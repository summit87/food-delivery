package com.orderservice.service;

import com.commons.enums.OrderStatusEnum;
import com.commons.enums.PaymentStatus;
import com.commons.enums.StatusMapping;
import com.commons.model.PaymentRequest;
import com.commons.model.PaymentResponse;
import com.commons.utils.GenericBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.client.PaymentServiceClient;
import com.orderservice.config.MessageSender;
import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.controller.response.OrderMetaInfo;
import com.orderservice.dao.OrderServiceDAO;
import com.orderservice.entity.OrderDetails;
import com.orderservice.entity.OrderItemDetails;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.models.OrderStatus;
import com.orderservice.models.PaymentStatusRequest;
import com.orderservice.models.PaymentStatusResponse;
import com.orderservice.models.ReceivedPaymentStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

	private OrderServiceDAO orderServiceDAO;
	private MessageSender messageSender;

	private IItemService itemServiceImpl;

	private ObjectMapper objectMapper;
	
	private PaymentServiceClient paymentServiceClient;

	public OrderServiceImpl(OrderServiceDAO orderServiceDAO,
			MessageSender messageSender, IItemService itemServiceImpl,
		ObjectMapper objectMapper, PaymentServiceClient paymentServiceClient) {

		this.orderServiceDAO = orderServiceDAO;
		this.messageSender = messageSender;
		this.itemServiceImpl = itemServiceImpl;
		this.objectMapper = objectMapper;
		this.paymentServiceClient = paymentServiceClient;
	}
	
	
	@Override
	public OrderStatus receivedPaymentStatus(
			ReceivedPaymentStatus receivedPaymentStatus) {
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrderStatus createOrder(OrderCreateRequest orderCreateRequest,
			HttpHeaders headers) {

		String orderId = createOrderId();
		String restaurantId = createRestaurantId();
		OrderDetails orderDetailsBuilder =
				GenericBuilder.of(OrderDetails::new)
						.with(OrderDetails::setOrderId, orderId)
						.with(OrderDetails::setOrderStatusEnum,
								OrderStatusEnum.ORDER_IN_PROGRESS)
						.with(OrderDetails::setPaymentStatus,
								PaymentStatus.INITIATED)
						.with(OrderDetails::setRestaurantId, restaurantId)
						.with(OrderDetails::setUserId,
								headers.getFirst("userId")).build();

		Set<OrderItemDetails> orderItemDetails
				= orderCreateRequest.getItemOrderRequests().stream()
				.map(availableItem ->
						GenericBuilder.of(OrderItemDetails::new)
								.with(OrderItemDetails::setItemId,
										availableItem.getItemId())
								.with(OrderItemDetails::setOrderDetails,
										orderDetailsBuilder).build())
				.collect(Collectors.toSet());

		BigDecimal totalAmount = itemServiceImpl.getAvailableItemsById(
						orderCreateRequest.getItemOrderRequests().stream()
								.map(items -> items.getItemId())
								.collect(
										Collectors.toList())).stream()
				.map(item -> item.getItemPrice())
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

		orderDetailsBuilder.setOrderItemDetails(orderItemDetails);
		orderDetailsBuilder.setTotalAmountNeedToPay(totalAmount);
		try {
			log.info("Started order creation for user id {} and order id {}",
					headers.getFirst("userId"), orderId);

			OrderDetails savedResult =
					orderServiceDAO.save(orderDetailsBuilder);

			PaymentRequest paymentRequest =
					GenericBuilder.of(PaymentRequest::new)
							.with(PaymentRequest::setOrderId, orderId)
							.with(PaymentRequest::setUserId,
									headers.getFirst("userId"))
							.with(PaymentRequest::setRestaurantId, restaurantId)
							.with(PaymentRequest::setTotalAmountNeedToPay,
									totalAmount).build();
			log.info("Started processing payment request for order id {} ",
				savedResult.getOrderId());
			PaymentResponse paymentResponse
				= paymentServiceClient.createAndProcessPayment(paymentRequest);
			log.info(
				"Payment request has been processed for order id {} , payment transaction id {} ",
				savedResult.getOrderId(), paymentResponse.getPaymentTransactionId());
			orderServiceDAO.updateOrderDetailsByOrderId(savedResult.getPaymentStatus(),
				paymentResponse.getPaymentStatus(), savedResult.getOrderStatusEnum(),
				OrderStatusEnum.ORDER_CREATED,
				savedResult.getOrderId());
			log.info("Order create for order id {} ", savedResult.getOrderId());
			return GenericBuilder.of(OrderStatus::new)
				.with(OrderStatus::setStatus, OrderStatusEnum.ORDER_IN_PROGRESS)
				.with(OrderStatus::setOrderId, savedResult.getOrderId())
				.with(OrderStatus::setUserId, headers.getFirst("userId"))
				.with(OrderStatus::setInstant, Instant.now())
				.with(OrderStatus::setPaidAmount, totalAmount)
				.with(OrderStatus::setPaymentId, paymentResponse.getPaymentTransactionId())
							.build();

		} catch (Exception ex) {
			log.error("Failed to execute order for given order id {}", orderId,
					ex);
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public PaymentStatusResponse updatePaymentStatus(
			PaymentStatusRequest paymentStatusRequest, HttpHeaders headers) {

		OrderStatusEnum toOrderStatus = StatusMapping.valueOf(
						paymentStatusRequest.getPaymentStatus().name())
				.getOrderStatusEnum();

		Optional<OrderDetails> optionalOrderDetails = orderServiceDAO.findById(
				paymentStatusRequest.getOrderId());
		if (!optionalOrderDetails.isPresent()) {

			log.error("Received payment status for order id {} not exist",
					paymentStatusRequest.getOrderId());
			throw new RuntimeException("Order not exist");
		}

		Integer rowUpdated = orderServiceDAO.updateOrderDetailsByOrderId(
				optionalOrderDetails.get().getPaymentStatus(),
				paymentStatusRequest.getPaymentStatus(),
				optionalOrderDetails.get().getOrderStatusEnum(),
				toOrderStatus,
				paymentStatusRequest.getOrderId());

		log.info(
				"Payment status updated for for order id {} and payment status {}",
				paymentStatusRequest.getOrderId(), toOrderStatus.name());

		PaymentStatusResponse build =
				GenericBuilder.of(PaymentStatusResponse::new)
						.with(PaymentStatusResponse::setPaymentStatus,
								toOrderStatus)
						.with(PaymentStatusResponse::setOrderId,
								paymentStatusRequest.getOrderId())
						.with(PaymentStatusResponse::setTxnId,
								paymentStatusRequest.getTxnId())
						.build();
		return build;
	}

	@Override
	public OrderMetaInfo findOrderStatusByOrderId(String orderId) {
		Optional<OrderDetails> orderDetails = orderServiceDAO.findById(orderId);
		if (!orderDetails.isPresent()) {
			throw new OrderNotFoundException(
					String.format("Order not found for given order id %s",
							orderId));
		}

		OrderDetails orderDetails1 = orderDetails.get();
		OrderMetaInfo orderMetaInfo = GenericBuilder.of(OrderMetaInfo::new)
				.with(OrderMetaInfo::setOrderStatusEnum,
						orderDetails1.getOrderStatusEnum())
				.with(OrderMetaInfo::setPaymentStatus,
						orderDetails1.getPaymentStatus())
				.with(OrderMetaInfo::setTotalPayedAmount,
						orderDetails1.getTotalAmountNeedToPay())
				.with(OrderMetaInfo::setItemMetaInfos,
						itemServiceImpl.getItemMetaInfos(
								orderDetails1.getOrderItemDetails()
										.stream()
										.map(orderItemDetails -> orderItemDetails.getItemId())
										.collect(Collectors.toList())))
				.build();
		return orderMetaInfo;
	}

	private String createOrderId() {
		long number = (long) Math.floor(
				Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return "O" + number;
	}

	private String createRestaurantId() {
		long number = (long) Math.floor(
				Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return "O" + number;
	}

}

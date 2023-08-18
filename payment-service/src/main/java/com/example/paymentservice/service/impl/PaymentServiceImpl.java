package com.example.paymentservice.service.impl;

import com.commons.enums.PaymentStatus;
import com.commons.model.PaymentRequest;
import com.commons.utils.GenericBuilder;
import com.example.paymentservice.client.OrderServiceClient;
import com.example.paymentservice.dao.PaymentServiceDAO;
import com.example.paymentservice.entity.PaymentStatusEntity;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.example.paymentservice.model.PaymentStatusResponseFromOrderService;
import com.example.paymentservice.service.IPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {

	private final PaymentServiceDAO paymentServiceDAO;
	private final OrderServiceClient orderServiceClient;

	public PaymentServiceImpl(PaymentServiceDAO paymentServiceDAO,
			OrderServiceClient orderServiceClient) {
		this.paymentServiceDAO = paymentServiceDAO;
		this.orderServiceClient = orderServiceClient;
	}

	@Override
	public PaymentStatusResponse savePaymentDetails(
			PaymentRequest paymentRequest) throws Exception {

		PaymentStatusEntity paymentStatus
				= GenericBuilder.of(PaymentStatusEntity::new)
				.with(PaymentStatusEntity::setPaymentStatus,
						PaymentStatus.APPROVED)
				.with(PaymentStatusEntity::setAmount,
						paymentRequest.getTotalAmountNeedToPay())
				.with(PaymentStatusEntity::setOrderId,
						paymentRequest.getOrderId())
				.with(PaymentStatusEntity::setRestaurantId,
						paymentRequest.getRestaurantId())
				.build();
		paymentStatus = paymentServiceDAO.save(paymentStatus);
		PaymentStatusResponse statusResponse =
				GenericBuilder.of(PaymentStatusResponse::new)
						.with(PaymentStatusResponse::setPaymentStatus,
								PaymentStatus.APPROVED)
						.with(PaymentStatusResponse::setAmount,
								paymentStatus.getAmount())
						.with(PaymentStatusResponse::setOrderId,
								paymentStatus.getOrderId())
						.with(PaymentStatusResponse::setTransactionId,
								paymentStatus.getTxnId())
						.with(PaymentStatusResponse::setRestaurantId,
								paymentStatus.getRestaurantId())
						.build();
		log.info("Order status updated for order id {} and status {}",
			statusResponse.getOrderId(), statusResponse.getPaymentStatus().name());
		return statusResponse;
	}

	@Override
	public PaymentStatusResponseFromOrderService postPaymentStatusToOrderService(
			PaymentStatusResponse request) throws JsonProcessingException {
		return orderServiceClient.postPaymentStatusToOrderService(request);
	}
}

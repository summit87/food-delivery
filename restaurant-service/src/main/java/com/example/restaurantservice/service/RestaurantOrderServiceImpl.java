package com.example.restaurantservice.service;

import com.commons.enums.Response;
import com.commons.enums.Status;
import com.commons.utils.GenericBuilder;
import com.example.restaurantservice.controller.request.RestaurantOrderDetails;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderRequest;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderResponse;
import com.example.restaurantservice.dao.RestaurantOrderDAO;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.exception.OrderNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantOrderServiceImpl
		implements IRestaurantOrderService {
	private final RestaurantOrderDAO restaurantOrderDAO;

	public RestaurantOrderServiceImpl(RestaurantOrderDAO restaurantOrderDAO) {
		this.restaurantOrderDAO = restaurantOrderDAO;
	}

	@Override
	public Response createOrderForRestaurant(
			RestaurantOrderDetails restaurantOrderDetails)
			throws JsonProcessingException {
		RestaurantOrder restaurantOrder =
				GenericBuilder.of(RestaurantOrder::new)
						.with(RestaurantOrder::setRestaurantId,
								restaurantOrderDetails.getRestaurantId())
						.with(RestaurantOrder::setOrderId,
								restaurantOrderDetails.getOrderId())
						.with(RestaurantOrder::setPaymentStatus,
								restaurantOrderDetails.getPaymentStatus())
						.with(RestaurantOrder::setCreatedBy, "SUMIT")
						.build();
		restaurantOrderDAO.save(restaurantOrder);

		return new Response.ResponseBuilder()
				.status(Status.SUCCESS, "0001")
				.responseBody(restaurantOrder).build();
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Response updateRestaurantOrder(String orderId,
			UpdateRestaurantOrderRequest order) {
		Optional<RestaurantOrder> optionalRestaurantOrder =
				restaurantOrderDAO.findById(orderId);
		if (!optionalRestaurantOrder.isPresent()) {
			throw new OrderNotFoundException("Order not found");
		}

		Integer updatedRowCount =
				restaurantOrderDAO.updateRestaurantOrderByOrderIdAndRestaurantId(
						order.getOrderStatus(), orderId,
						order.getRestaurantId(),
						optionalRestaurantOrder.get()
								.getRestaurantOrderStatus());

		if (updatedRowCount <= 0) {
			throw new RuntimeException(
					"Order if found in more than one restaurant");
		}

		UpdateRestaurantOrderResponse
				response = GenericBuilder.of(UpdateRestaurantOrderResponse::new)
				.with(UpdateRestaurantOrderResponse::setOrderId, orderId)
				.with(UpdateRestaurantOrderResponse::setMessage, "Updated")
				.build();
		log.info("Restaurant order updated , restaurant id {} , order id {}",
				order.getRestaurantId(), orderId);
		return new Response.ResponseBuilder<UpdateRestaurantOrderResponse>()
				.responseBody(response)
				.status(Status.SUCCESS, "002")
				.build();
	}
}

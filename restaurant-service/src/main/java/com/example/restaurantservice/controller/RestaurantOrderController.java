package com.example.restaurantservice.controller;

import com.commons.enums.Response;
import com.example.restaurantservice.controller.request.RestaurantOrderDetails;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderRequest;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderResponse;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.service.IRestaurantOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "restaurant")
@Slf4j
public class RestaurantOrderController implements IRestaurantOrderController {
	
	private IRestaurantOrderService restaurantOrderServiceImpl;
	
	public RestaurantOrderController(
		IRestaurantOrderService restaurantOrderServiceImpl) {
		this.restaurantOrderServiceImpl = restaurantOrderServiceImpl;
	}
	
	@Override
	public Response<RestaurantOrder> createOrderForRestaurant(
		RestaurantOrderDetails restaurantOrderDetails)
		throws JsonProcessingException {
		log.info(
			"Restaurant order received for order id {} and restaurant id {} ",
			restaurantOrderDetails.getOrderId(),
			restaurantOrderDetails.getRestaurantId());
		return restaurantOrderServiceImpl.createOrderForRestaurant(
			restaurantOrderDetails);
	}
	
	@Override
	public Response<UpdateRestaurantOrderResponse> updateRestaurantOrder(String orderId,
		UpdateRestaurantOrderRequest order) {
		return restaurantOrderServiceImpl.updateRestaurantOrder(orderId, order);
	}
	
}

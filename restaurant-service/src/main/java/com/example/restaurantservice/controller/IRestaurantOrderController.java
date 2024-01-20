package com.example.restaurantservice.controller;

import com.commons.enums.Response;
import com.example.restaurantservice.controller.request.RestaurantOrderDetails;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderRequest;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderResponse;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestaurantOrderController {
	
	@PostMapping(value = "/createRestaurantOrder")
	Response<RestaurantOrder> createOrderForRestaurant(
		@RequestBody RestaurantOrderDetails restaurantOrderDetails)
		throws JsonProcessingException;
	
	@PostMapping(value = "/updateOrder/{orderId}")
	Response<UpdateRestaurantOrderResponse> updateRestaurantOrder(@PathVariable("orderId") String orderId, @RequestBody
	UpdateRestaurantOrderRequest order);
	
}

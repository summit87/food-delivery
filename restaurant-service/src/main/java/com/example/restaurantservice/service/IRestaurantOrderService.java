package com.example.restaurantservice.service;

import com.commons.enums.Response;
import com.example.restaurantservice.controller.request.RestaurantOrderDetails;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRestaurantOrderService {
	Response createOrderForRestaurant(
			RestaurantOrderDetails restaurantOrderDetails)
			throws JsonProcessingException;

	Response updateRestaurantOrder(String orderId, UpdateRestaurantOrderRequest order);
}

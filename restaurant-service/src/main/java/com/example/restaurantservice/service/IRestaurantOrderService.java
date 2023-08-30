package com.example.restaurantservice.service;

import com.commons.enums.Response;
import com.example.restaurantservice.controller.request.RestaurantOrderDetails;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderRequest;
import com.example.restaurantservice.controller.request.UpdateRestaurantOrderResponse;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRestaurantOrderService {
	Response<RestaurantOrder> createOrderForRestaurant(
			RestaurantOrderDetails restaurantOrderDetails)
			throws JsonProcessingException;

	Response<UpdateRestaurantOrderResponse> updateRestaurantOrder(String orderId, UpdateRestaurantOrderRequest order);
}

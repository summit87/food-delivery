package com.example.restaurantservice.command.service;

import com.example.restaurantservice.command.RestaurantServiceCommandName;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.model.RestaurantEventDetails;

public interface RestaurantOrderService {
	
	RestaurantOrder acceptEventAndProcessMessage(RestaurantEventDetails restaurantEventDetails);
	
	RestaurantOrder updateOrderServiceByOrderId(String orderId);
	
	RestaurantServiceCommandName getCommandName();
}

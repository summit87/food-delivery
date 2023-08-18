package com.example.restaurantservice.command.service;

import com.example.restaurantservice.command.RestaurantServiceCommandName;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.model.RestaurantEventDetails;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface RestaurantOrderService {
	RestaurantOrder acceptEventAndUpdateOrder(RestaurantEventDetails restaurantEventDetails);

	RestaurantOrder updateOrderServiceByOrderId(String orderId);

	RestaurantServiceCommandName getCommandName();
}

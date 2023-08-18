package com.example.restaurantservice.command;

import com.example.restaurantservice.command.service.RestaurantOrderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantCommandFactory {

	List<RestaurantOrderService> orderServiceList;

	public RestaurantCommandFactory(
			List<RestaurantOrderService> orderServiceList) {
		this.orderServiceList = orderServiceList;
	}

	public RestaurantOrderService getRestaurantCommandService(
			RestaurantServiceCommandName commandName) {

		RestaurantOrderService unknownCommand = orderServiceList
				.stream()
				.filter(entry -> entry.getCommandName().equals(commandName))
				.findAny()
				.orElseThrow(() -> new RuntimeException("Unknown command"));
		return unknownCommand;
	}
}

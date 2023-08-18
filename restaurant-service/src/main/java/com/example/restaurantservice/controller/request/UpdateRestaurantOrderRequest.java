package com.example.restaurantservice.controller.request;

import com.commons.enums.RestaurantOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateRestaurantOrderRequest {
	private RestaurantOrderStatus orderStatus;
	private String restaurantId;
}

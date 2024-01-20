package com.fooddelivery.authserver.restaurent.controller;

import com.commons.enums.Response;
import com.commons.model.RestaurantProfile;
import com.commons.response.ApiSuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestaurantProfile {

	@PostMapping(value = "registerRestaurant")
	public Response<ApiSuccessResponse> profile(@RequestBody RestaurantProfile restaurantProfile);
}

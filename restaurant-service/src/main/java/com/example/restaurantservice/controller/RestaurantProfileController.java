package com.example.restaurantservice.controller;

import com.commons.enums.Response;
import com.commons.restaurant.RestaurantProfile;
import com.commons.restaurant.RestaurantProfileResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import reactor.netty.http.server.HttpServerRequest;

public class RestaurantProfileController implements IRestaurantProfile {
    @Override
    public ResponseEntity<Response<RestaurantProfileResponse>> createRestaurantProfile(RestaurantProfile restaurantProfile, HttpServerRequest request, HttpHeaders headers) {
        return null;
    }
}

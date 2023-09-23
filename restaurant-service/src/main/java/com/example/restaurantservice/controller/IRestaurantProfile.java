package com.example.restaurantservice.controller;

import com.commons.enums.Response;
import com.commons.restaurant.RestaurantProfile;
import com.commons.restaurant.RestaurantProfileResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.netty.http.server.HttpServerRequest;

public interface IRestaurantProfile {
  @PostMapping(path = "createProfile")
  ResponseEntity<Response<RestaurantProfileResponse>> createRestaurantProfile(
      @RequestBody RestaurantProfile restaurantProfile,
      HttpServerRequest request,
      @RequestHeader HttpHeaders headers);
}

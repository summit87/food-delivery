package com.example.restaurantservice.controller;

import com.commons.enums.Response;
import com.commons.response.ApiSuccessResponse;
import com.commons.utils.GenericBuilder;
import com.example.restaurantservice.entity.RestaurantProfile;
import com.example.restaurantservice.service.IRestaurantProfileService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "restaurant")
@Slf4j
public class RestaurantProfileService implements IRestaurantProfile {

  private IRestaurantProfileService restaurantProfileServiceImpl;

  public RestaurantProfileService(IRestaurantProfileService restaurantProfileServiceImpl) {
    this.restaurantProfileServiceImpl = restaurantProfileServiceImpl;
  }

  @Override
  public Response<ApiSuccessResponse> profile(
      com.commons.model.RestaurantProfile restaurantProfile) {
    RestaurantProfile restaurantProfileService =
        restaurantProfileServiceImpl.createProfile(restaurantProfile);
    ApiSuccessResponse build =
        GenericBuilder.of(ApiSuccessResponse::new)
            .with(ApiSuccessResponse::setCode, HttpStatus.OK.name())
            .with(
                ApiSuccessResponse::setMessage,
                String.format(
                    "Restaurant %s created and id %s ",
                    restaurantProfileService.getRestaurantName(),
                    restaurantProfileService.getRestaurantId()))
            .build();
    return new Response.ResponseBuilder<ApiSuccessResponse>().responseBody(build).build();
  }
}

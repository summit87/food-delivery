package com.example.restaurantservice.entity;

import java.io.Serializable;
import java.time.Instant;
import lombok.Value;

/** DTO for {@link RestaurantAddress} */
@Value
public class RestaurantAddressDto implements Serializable {

  String restaurantAddressId;
  RestaurantProfileDto restaurant;
  String address;
  String streetName;
  String landmark;
  String pinCode;
  Instant createTs;
  Instant lastUpdatedTs;
  String createBy;
  String updatedBy;
}

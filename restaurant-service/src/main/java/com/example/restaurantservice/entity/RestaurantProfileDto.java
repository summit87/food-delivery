package com.example.restaurantservice.entity;

import java.io.Serializable;
import java.time.Instant;
import lombok.Value;

/** DTO for {@link RestaurantProfile} */
@Value
public class RestaurantProfileDto implements Serializable {

  String restaurantId;
  String restaurantName;
  String prmryMobNum;
  String secndyMobNum;
  String emailAddress;
  Instant createTs;
  Instant lastUpdatedTs;
  String createBy;
  String updatedBy;
}

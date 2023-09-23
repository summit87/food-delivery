package com.commons.restaurant;

import com.commons.model.RestaurantAddress;
import com.commons.model.RestaurantContact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class RestaurantProfile {

    String restaurantName;
    List<FoodCategory> foodCategories;
    RestaurantAddress restaurantAddress;
    RestaurantContact restaurantContact;

}

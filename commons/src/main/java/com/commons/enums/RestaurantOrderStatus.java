package com.commons.enums;

import com.commons.enumSerialization.RestaurantOrderStatusSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = RestaurantOrderStatusSerialization.class)
public enum RestaurantOrderStatus {
	ORDER_CONFIRMED, ORDER_DENIED, ORDER_IN_PROGRESS
}

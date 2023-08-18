package com.example.restaurantservice.model;

import com.commons.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantEventDetails {
	private String orderId;
	private String restaurantId;
	private BigDecimal amount;
	private BigDecimal customerBillableAmount;
	private PaymentStatus paymentStatus;
}

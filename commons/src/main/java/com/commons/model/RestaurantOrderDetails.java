package com.commons.model;

import com.commons.enums.DeliveryStatus;
import com.commons.enums.PaymentStatus;
import com.commons.enums.RestaurantOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class RestaurantOrderDetails {
	
	@JsonProperty
	String orderId;
	
	@JsonProperty
	String restaurantId;
	
	@JsonProperty
	PaymentStatus paymentStatus;
	
	@JsonProperty
	DeliveryStatus deliveryStatus;
	
	@JsonProperty
	RestaurantOrderStatus restaurantOrderStatus;
	
	@JsonProperty
	private BigDecimal totalDeliveryCharge;
	
	@JsonProperty
	Instant createTs;
	
	@JsonProperty
	String createdBy;
	
}

package com.example.deliveryservice.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryDetailsPk implements Serializable {
	
	@Column(name = "order_id")
	String orderId;
	
	@Column(name = "restaurant_id")
	String restaurantId;
	
}

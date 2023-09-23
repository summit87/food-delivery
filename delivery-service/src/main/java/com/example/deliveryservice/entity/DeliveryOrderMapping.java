package com.example.deliveryservice.entity;

import static com.example.deliveryservice.constants.DatabaseConstants.DELIVERY_ORDER_MAPPING;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DELIVERY_ORDER_MAPPING, uniqueConstraints = {
	@UniqueConstraint(columnNames = {"order_id", "restaurant_id"})})
@Getter
@Setter
public class DeliveryOrderMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id", nullable = false)
	private Integer deliveryId;
	@Column(name = "order_id", nullable = false)
	private String orderId;
	@Column(name = "restaurant_id", nullable = false)
	private String restaurantId;
	
}

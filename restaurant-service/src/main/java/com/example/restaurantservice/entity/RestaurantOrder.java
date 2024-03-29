package com.example.restaurantservice.entity;

import com.commons.enums.DeliveryStatus;
import com.commons.enums.PaymentStatus;
import com.commons.enums.RestaurantOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "restaurant")
public class RestaurantOrder {
	
	@Id
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "restaurant_id")
	private String restaurantId;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Column(name = "delivery_status")
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	@Column(name = "restaurant_order_status")
	@Enumerated(EnumType.STRING)
	private RestaurantOrderStatus restaurantOrderStatus;
	
	@Column(name = "create_ts", insertable = true, updatable = false)
	private Instant createTs;
	
	@Column(name = "last_updated_ts", insertable = false, updatable = true)
	private Instant lastUpdateTs;
	
	@Column(name = "create_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String lastUpdatedBy;
	
	@PrePersist
	void onCreate() {
		this.setCreateTs(Instant.now());
	}
	
	@PostPersist
	void onUpdate() {
		this.setLastUpdateTs(Instant.now());
	}
	
}

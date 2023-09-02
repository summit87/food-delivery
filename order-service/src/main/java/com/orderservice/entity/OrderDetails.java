package com.orderservice.entity;

import com.commons.enums.OrderStatusEnum;
import com.commons.enums.PaymentStatus;
import com.orderservice.utils.DatabaseConstants;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = DatabaseConstants.ORDER_DETAILS)
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails extends AuditModel {
	
	@Id
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "order_status")
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum orderStatusEnum;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "total_amount")
	private BigDecimal totalAmountNeedToPay;
	
	@Column(name = "restaurant_id")
	private String restaurantId;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@Setter
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderDetails", cascade = {CascadeType.ALL})
	private Set<OrderItemDetails> orderItemDetails = new HashSet<>(0);
}

package com.example.paymentservice.entity;

import com.commons.enums.PaymentStatus;
import com.example.paymentservice.utils.DatabaseConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = DatabaseConstants.PAYMENT_STATUS)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentStatusEntity {
	
	@Column(name = "transaction_id")
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String txnId;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "item_price")
	private BigDecimal amount;
	
	@Column(name = "restaurant_id")
	private String restaurantId;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
}

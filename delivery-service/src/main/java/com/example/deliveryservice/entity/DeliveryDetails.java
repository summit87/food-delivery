package com.example.deliveryservice.entity;


import com.commons.enums.DeliveryStatus;
import com.commons.enums.PaymentStatus;
import com.example.deliveryservice.constants.DatabaseConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = DatabaseConstants.DELIVERY_DETAILS)
@Getter
@Setter
@NoArgsConstructor
public class DeliveryDetails extends AuditModel {
	
	@EmbeddedId
	private DeliveryDetailsPk deliveryDetailsPk;
	
	@Column(name = "delivery_status")
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	@Column(name = "delivery_charge_payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Column(name = "delivery_charge")
	private BigDecimal deliveryCharge;
	
}

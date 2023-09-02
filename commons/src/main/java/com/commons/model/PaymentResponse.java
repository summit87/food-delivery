package com.commons.model;

import com.commons.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
	
	private String paymentTransactionId;
	private BigDecimal totalDeductedAmount;
	private PaymentStatus paymentStatus;
	private Instant transactionDate;
	private String orderId;
	private String restaurantId;
	
}

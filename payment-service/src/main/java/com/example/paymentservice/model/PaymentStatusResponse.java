package com.example.paymentservice.model;

import com.commons.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentStatusResponse {
	
	private String orderId;
	private String transactionId;
	private BigDecimal amount;
	private PaymentStatus paymentStatus;
	private String restaurantId;
}

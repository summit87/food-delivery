package com.orderservice.models;

import com.commons.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class PaymentStatusRequest {
	
	private String orderId;
	private String txnId;
	
	private PaymentStatus paymentStatus;
}

package com.example.paymentservice.model;


import com.commons.enums.OrderStatusEnum;
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
public class PaymentStatusResponseFromOrderService {
	
	private String orderId;
	private String txnId;
	
	private OrderStatusEnum paymentStatus;
}

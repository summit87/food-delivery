package com.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OrderStatus {
	
	private Enum status;
	private String message;
	private Instant instant;
	private String orderId;
	private String userId;
	private BigDecimal paidAmount;
	private String paymentId;
}

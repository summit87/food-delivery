package com.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class PaymentRequest {
	
	private String orderId;
	private String userId;
	private String restaurantId;
	private BigDecimal totalAmountNeedToPay;
}
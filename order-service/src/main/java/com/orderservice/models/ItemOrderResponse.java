package com.orderservice.models;

import com.commons.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemOrderResponse {
	
	private String orderId;
	private OrderStatusEnum orderStatus;
	
}

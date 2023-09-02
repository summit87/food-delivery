package com.orderservice.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.orderservice.models.ItemOrderRequest;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCreateRequest {
	
	List<ItemOrderRequest> itemOrderRequests;
	private String restaurantId;
	
}

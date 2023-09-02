package com.orderservice.models;

import com.commons.enums.ItemAvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailableItem {
	
	private String itemName;
	private String itemType;
	private String itemId;
	private BigDecimal itemPrice;
	private ItemAvailabilityStatus itemAvailabilityStatus;
	
}

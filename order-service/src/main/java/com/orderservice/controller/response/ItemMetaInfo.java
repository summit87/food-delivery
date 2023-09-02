package com.orderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemMetaInfo {
	
	private String itemName;
	private BigDecimal itemCost;
	private int itemQty = 1;
	
}

package com.commons.delivery;


import com.commons.enums.DeliveryStatus;
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
public class DeliveryServiceStatus {
	String orderId;
	String restaurantId;
	DeliveryStatus deliveryStatus;
}

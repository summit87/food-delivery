package com.commons.enums;

import com.commons.enumSerialization.DeliveryStatusSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DeliveryStatusSerialization.class)
public enum DeliveryStatus {
	DELIVERY_CONFIRMED,
	DELIVERY_REJECTED,
	DELIVERY_PENDING,
	DELIVERY_COMPLETED
}

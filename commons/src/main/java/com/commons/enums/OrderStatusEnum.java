package com.commons.enums;

import com.commons.enumSerialization.StatusTypeSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = StatusTypeSerialization.class)
public enum OrderStatusEnum {
	ORDER_CREATED, ORDER_CONFIRMED, ORDER_FAILED, ORDER_CANCELED,
	ORDER_IN_PROGRESS, ORDER_PICKED, ORDER_DELIVERED
}

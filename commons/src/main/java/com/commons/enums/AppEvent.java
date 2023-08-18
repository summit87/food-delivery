package com.commons.enums;

import com.commons.enumSerialization.AppEventSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = AppEventSerialization.class)
public enum AppEvent {
	RESTAURANT_ORDER_ACCEPT,
	RESTAURANT_ORDER_REJECTED,
	RESTAURANT_ORDER_CANCELED,
	PAYMENT_CANCELED,
	ORDER_CANCELED
}

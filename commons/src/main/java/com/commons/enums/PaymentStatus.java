package com.commons.enums;

import com.commons.enumSerialization.PaymentTypeSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PaymentTypeSerialization.class)
public enum PaymentStatus {
	RECEIVED, DECLINED, APPROVED, INITIATED, PENDING
}

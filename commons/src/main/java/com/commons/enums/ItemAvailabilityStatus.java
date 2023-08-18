package com.commons.enums;

import com.commons.enumSerialization.ItemAvailabilityStatusTypeSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ItemAvailabilityStatusTypeSerialization.class)
public enum ItemAvailabilityStatus {
	AVAILABLE, NOT_AVAILABLE
}

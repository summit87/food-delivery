package com.commons.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = StatusSerialization.class)
public enum Status {
	FAILED, SUCCESS, PENDING
}

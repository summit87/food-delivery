package com.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.commons.enums.OrderStatusEnum.*;

@Getter
@AllArgsConstructor
public enum StatusMapping {
	APPROVED(ORDER_CONFIRMED),
	DECLINED(ORDER_FAILED), PENDING(ORDER_IN_PROGRESS);
	private OrderStatusEnum orderStatusEnum;
}

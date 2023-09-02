package com.commons.enums;

import static com.commons.enums.OrderStatusEnum.ORDER_CONFIRMED;
import static com.commons.enums.OrderStatusEnum.ORDER_FAILED;
import static com.commons.enums.OrderStatusEnum.ORDER_IN_PROGRESS;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMapping {
	APPROVED(ORDER_CONFIRMED),
	DECLINED(ORDER_FAILED), PENDING(ORDER_IN_PROGRESS);
	private OrderStatusEnum orderStatusEnum;
}

package com.commons.enums;

import com.commons.enumSerialization.OrderPreparationStatusSerialization;
import com.commons.enumSerialization.StatusTypeSerialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OrderPreparationStatusSerialization.class)
public enum OrderPreparationStatus {
  ORDER_PREPARATION_STARTED, ORDER_PREPARATION_COMPLETED, ORDER_PREPARATION_INCOMPLETE, ORDER_PREPARATION_IN_PROGRESS
}

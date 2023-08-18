package com.orderservice.controller.response;

import com.commons.enums.OrderStatusEnum;
import com.commons.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderMetaInfo {
	private Instant createTs;
	private PaymentStatus paymentStatus;
	private BigDecimal totalPayedAmount;
	private OrderStatusEnum orderStatusEnum;
	private List<ItemMetaInfo> itemMetaInfos;
}

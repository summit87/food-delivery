package com.orderservice.service;

import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.controller.response.OrderMetaInfo;
import com.orderservice.models.OrderStatus;
import com.orderservice.models.PaymentStatusRequest;
import com.orderservice.models.PaymentStatusResponse;
import com.orderservice.models.ReceivedPaymentStatus;
import org.springframework.http.HttpHeaders;

public interface IOrderService {
	
	OrderStatus receivedPaymentStatus(
		ReceivedPaymentStatus receivedPaymentStatus);
	
	OrderStatus createOrder(OrderCreateRequest orderCreateRequest, HttpHeaders headers);
	
	PaymentStatusResponse updatePaymentStatus(PaymentStatusRequest paymentStatusRequest,
		HttpHeaders headers);
	
	OrderMetaInfo findOrderStatusByOrderId(String orderId);
}

package com.example.paymentservice.service;

import com.commons.model.PaymentRequest;
import com.commons.payment.OrderPaymentStatus;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.example.paymentservice.model.PaymentStatusResponseFromOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPaymentService {
	
	PaymentStatusResponse savePaymentDetails(PaymentRequest paymentRequest) throws Exception;
	
	PaymentStatusResponseFromOrderService postPaymentStatusToOrderService(
		PaymentStatusResponse request) throws JsonProcessingException;
	
	OrderPaymentStatus getPaymentStatus(String orderId, String restaurantId);
}

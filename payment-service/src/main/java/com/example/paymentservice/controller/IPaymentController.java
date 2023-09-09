package com.example.paymentservice.controller;

import com.commons.enums.Response;
import com.commons.model.PaymentRequest;
import com.commons.model.PaymentResponse;
import com.commons.payment.OrderPaymentStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IPaymentController {
	
	
	@PostMapping(value = "/processPayment")
	Response<PaymentResponse> processPaymentRequest(@RequestBody PaymentRequest request);
	
	@GetMapping(value = "/get/{orderId}/{restaurantId}")
	Response<OrderPaymentStatus> getPaymentStatus(@PathVariable("orderId")String orderId,@PathVariable("restaurantId") String restaurantId);
	
}

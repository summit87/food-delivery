package com.example.paymentservice.controller;

import com.commons.enums.Response;
import com.commons.model.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IPaymentController {
	
	
	@PostMapping(value = "/processPayment")
	Response processPaymentRequest(@RequestBody PaymentRequest request);
}

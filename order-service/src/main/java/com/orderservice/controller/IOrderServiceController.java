package com.orderservice.controller;

import com.commons.enums.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.models.OrderStatus;
import com.orderservice.models.PaymentStatusRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


public interface IOrderServiceController {

	@PostMapping(path = "/createOrder")
	OrderStatus createOrder(@RequestBody OrderCreateRequest itemOrderRequests,
			@RequestHeader HttpHeaders headers);

	@PostMapping(path = "/paymentStatus")
	Response updatePaymentStatus(@RequestBody PaymentStatusRequest paymentStatusRequest,@RequestHeader HttpHeaders headers)
			throws JsonProcessingException;

	@GetMapping(path = "status/{orderId}")
	Response getOrderStatusByOrderId(@PathVariable("orderId") String orderId)
			throws JsonProcessingException;


}

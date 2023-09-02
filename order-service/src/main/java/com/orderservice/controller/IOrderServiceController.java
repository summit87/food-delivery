package com.orderservice.controller;

import com.commons.enums.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.controller.response.OrderMetaInfo;
import com.orderservice.models.OrderStatus;
import com.orderservice.models.PaymentStatusRequest;
import com.orderservice.models.PaymentStatusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


public interface IOrderServiceController {
	
	@PostMapping(path = "/createOrder")
	OrderStatus createOrder(@RequestBody OrderCreateRequest itemOrderRequests,
		@RequestHeader HttpHeaders headers);
	
	@PostMapping(path = "/paymentStatus")
	Response<PaymentStatusResponse> updatePaymentStatus(
		@RequestBody PaymentStatusRequest paymentStatusRequest, @RequestHeader HttpHeaders headers)
		throws JsonProcessingException;
	
	@GetMapping(path = "status/{orderId}")
	Response<OrderMetaInfo> getOrderStatusByOrderId(@PathVariable("orderId") String orderId)
		throws JsonProcessingException;
	
	
}

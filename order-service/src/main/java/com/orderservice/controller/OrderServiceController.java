package com.orderservice.controller;

import com.commons.enums.Response;
import com.commons.enums.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.controller.response.OrderMetaInfo;
import com.orderservice.models.OrderStatus;
import com.orderservice.models.PaymentStatusRequest;
import com.orderservice.models.PaymentStatusResponse;
import com.orderservice.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "order")
@Slf4j
public class OrderServiceController implements IOrderServiceController {

	private IOrderService orderServiceImpl;

	public OrderServiceController(IOrderService orderServiceImpl) {
		this.orderServiceImpl = orderServiceImpl;
	}

	@Override
	public OrderStatus createOrder(OrderCreateRequest orderCreateRequest,
			HttpHeaders headers) {
		return orderServiceImpl.createOrder(orderCreateRequest, headers);
	}

	@Override
	public Response<PaymentStatusResponse> updatePaymentStatus(
			PaymentStatusRequest paymentStatusRequest, HttpHeaders headers)
			throws JsonProcessingException {
		try {
			PaymentStatusResponse paymentStatusResponse =
					orderServiceImpl.updatePaymentStatus(paymentStatusRequest,
							headers);
			return new Response.ResponseBuilder<PaymentStatusResponse>()
					.status(Status.SUCCESS, "0001")
					.responseBody(paymentStatusResponse).build();
		} catch (Exception ex) {
			log.error("Order service error : ",ex);
			throw ex;
		}
	}

	@Override
	public Response<OrderMetaInfo> getOrderStatusByOrderId(String orderId)
			throws JsonProcessingException {
		try {
			OrderMetaInfo orderMetaInfo =
					orderServiceImpl.findOrderStatusByOrderId(orderId);
			return new Response.ResponseBuilder<OrderMetaInfo>()
					.status(Status.SUCCESS, "0001")
					.responseBody(orderMetaInfo)
					.build();
		} catch (Exception ex) {
			throw ex;
		}

	}

}

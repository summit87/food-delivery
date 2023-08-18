package com.orderservice.service;
import com.orderservice.controller.request.OrderCreateRequest;
import com.orderservice.controller.response.OrderMetaInfo;
import com.orderservice.entity.OrderDetails;
import com.orderservice.models.*;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface IOrderService {

	OrderStatus receivedPaymentStatus(
			ReceivedPaymentStatus receivedPaymentStatus);

	OrderStatus createOrder(OrderCreateRequest orderCreateRequest, HttpHeaders headers);

	PaymentStatusResponse updatePaymentStatus(PaymentStatusRequest paymentStatusRequest, HttpHeaders headers);

	OrderMetaInfo findOrderStatusByOrderId(String orderId);
}

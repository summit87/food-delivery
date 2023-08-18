package com.example.paymentservice.controller;

import com.commons.enums.Response;
import com.commons.enums.Status;
import com.commons.model.PaymentRequest;
import com.commons.model.PaymentResponse;
import com.commons.utils.GenericBuilder;
import com.example.paymentservice.exception.PaymentFailedException;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.example.paymentservice.service.consumerUtils.ProducerServiceUtils;
import com.example.paymentservice.service.impl.PaymentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "payment")
@Slf4j
public class PaymentController implements IPaymentController {
	
	private final PaymentServiceImpl paymentServiceImpl;
	private final ObjectMapper objectMapper;
	private final ProducerServiceUtils producerServiceUtils;
	
	public PaymentController(PaymentServiceImpl paymentServiceImpl, ObjectMapper objectMapper,
		ProducerServiceUtils producerServiceUtils) {
		this.paymentServiceImpl = paymentServiceImpl;
		this.objectMapper = objectMapper;
		this.producerServiceUtils = producerServiceUtils;
	}
	
	@Override
	public Response processPaymentRequest(PaymentRequest request) {
		try {
			
			log.info("Payment started for order id {} ",
				request.getOrderId());
			PaymentStatusResponse paymentStatusResponse =
				paymentServiceImpl.savePaymentDetails(request);
			log.info("Payment completed for order id {} and transaction id {}",
				paymentStatusResponse.getOrderId(),
				paymentStatusResponse.getTransactionId());
			/**
			 * Publish the event to restaurant service
			 * Once restaurant service will receive the event ,
			 * Publish the event to respective restaurant id
			 */
			producerServiceUtils.publishEvent(paymentStatusResponse);
			PaymentResponse response = GenericBuilder.of(PaymentResponse::new)
				.with(PaymentResponse::setPaymentStatus, paymentStatusResponse.getPaymentStatus())
				.with(PaymentResponse::setPaymentTransactionId,
					paymentStatusResponse.getTransactionId())
				.with(PaymentResponse::setTotalDeductedAmount, paymentStatusResponse.getAmount())
				.with(PaymentResponse::setTransactionDate, Instant.now())
				.with(PaymentResponse::setOrderId, paymentStatusResponse.getOrderId())
				.with(PaymentResponse::setRestaurantId, paymentStatusResponse.getRestaurantId())
				.build();
			return new Response.ResponseBuilder<PaymentResponse>()
				.responseBody(response)
				.status(Status.SUCCESS, "001")
				.build();
		} catch (Exception ex) {
			log.error("Error while making payment ", ex);
			throw new PaymentFailedException(ex.getMessage());
		}
	}
}

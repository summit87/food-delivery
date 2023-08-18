package com.example.paymentservice.client;

import com.commons.enums.Response;
import com.commons.utils.GenericBuilder;
import com.example.paymentservice.model.PaymentStatusRequestToOrderService;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.example.paymentservice.model.PaymentStatusResponseFromOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderServiceClient {

	private ClientProperties clientProperties;

	private RestTemplate restTemplate;
	
	private RetryTemplate retryTemplate;

	private ObjectMapper objectMapper;

	public OrderServiceClient(ClientProperties clientProperties,
		RestTemplate restTemplate, RetryTemplate retryTemplate,
			ObjectMapper objectMapper) {
		this.clientProperties = clientProperties;
		this.restTemplate = restTemplate;
		this.retryTemplate = retryTemplate;
		this.objectMapper = objectMapper;
	}

	public PaymentStatusResponseFromOrderService postPaymentStatusToOrderService(
			PaymentStatusResponse paymentStatusResponse)
			throws JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		PaymentStatusRequestToOrderService requestToOrderService
				= GenericBuilder.of(PaymentStatusRequestToOrderService::new)
				.with(PaymentStatusRequestToOrderService::setPaymentStatus,
						paymentStatusResponse.getPaymentStatus())
				.with(PaymentStatusRequestToOrderService::setOrderId,
						paymentStatusResponse.getOrderId())
				.with(PaymentStatusRequestToOrderService::setTxnId,
						paymentStatusResponse.getTransactionId())
				.build();
		HttpEntity<PaymentStatusRequestToOrderService> entity =
				new HttpEntity<>(requestToOrderService, headers);
		String url = String.format("%s/%s", clientProperties.getBaseUrl(),
				clientProperties.getPostPaymentStatusPath());
		ResponseEntity<Response<PaymentStatusResponseFromOrderService>>
				execute =
			retryTemplate.execute(
						(context) -> {
							try {
								ParameterizedTypeReference<Response<PaymentStatusResponseFromOrderService>>
										parameterizedTypeReference =
										new ParameterizedTypeReference<Response<PaymentStatusResponseFromOrderService>>() {
										};
								
								return restTemplate.exchange(url, HttpMethod.POST, entity,
									parameterizedTypeReference);
							} catch (Exception ex) {
								throw new RuntimeException(ex);
							}
						});
		PaymentStatusResponseFromOrderService orderPaymentStatus = null;
		if (execute.getStatusCode().is2xxSuccessful()) {
			return execute.getBody().getResponseBody();
		}
		return orderPaymentStatus;
	}
}

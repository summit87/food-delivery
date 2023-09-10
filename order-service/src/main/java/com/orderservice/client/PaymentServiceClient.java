package com.orderservice.client;

import com.commons.enums.Response;
import com.commons.model.PaymentRequest;
import com.commons.model.PaymentResponse;
import com.orderservice.config.PaymentServiceProperties;
import com.orderservice.exception.PaymentFailedException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentServiceClient {
	
	private final RestTemplate restTemplate;
	private final RetryTemplate retryTemplate;
	private final PaymentServiceProperties paymentServiceProperties;
	
	public PaymentServiceClient(RestTemplate restTemplate, RetryTemplate retryTemplate,
		PaymentServiceProperties paymentServiceProperties) {
		this.restTemplate = restTemplate;
		this.retryTemplate = retryTemplate;
		this.paymentServiceProperties = paymentServiceProperties;
	}
	
	public PaymentResponse createAndProcessPayment(PaymentRequest paymentRequest) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, httpHeaders);
		
		String url = String.format("%s/%s", paymentServiceProperties.getBaseUrl(),
			paymentServiceProperties.getCreatePayment());
		ResponseEntity<Response<PaymentResponse>> response
			= retryTemplate.execute((retryContext) -> {
			try {
				ParameterizedTypeReference<Response<PaymentResponse>>
					parameterizedTypeReference =
					new ParameterizedTypeReference<>() {
					};
				return restTemplate.exchange(url, HttpMethod.POST, entity,
					parameterizedTypeReference);
			} catch (HttpClientErrorException e) {
				throw new PaymentFailedException(e.getResponseBodyAsString());
			} catch (Exception exception) {
				throw new RuntimeException(exception.getMessage());
			}
		});
		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Unknown error");
		}
		return Objects.requireNonNull(response.getBody()).getResponseBody();
	}
	
	
}

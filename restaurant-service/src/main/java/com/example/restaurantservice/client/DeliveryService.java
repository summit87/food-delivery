package com.example.restaurantservice.client;

import com.commons.delivery.DeliveryServiceStatus;
import com.commons.delivery.RestaurantOrderDetails;
import com.commons.enums.Response;
import com.commons.utils.GenericBuilder;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.exception.DeliveryFailedException;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DeliveryService {
	
	private final RestTemplate restTemplate;
	
	private final RetryTemplate retryTemplate;
	private final ClientProperties clientProperties;
	
	public DeliveryService(RestTemplate restTemplate, RetryTemplate retryTemplate,
		ClientProperties clientProperties) {
		this.restTemplate = restTemplate;
		this.retryTemplate = retryTemplate;
		this.clientProperties = clientProperties;
	}
	
	public DeliveryServiceStatus createDeliveryDetailsForTheOrder(
		RestaurantOrder restaurantOrderDetails) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(List.of(MediaType.APPLICATION_JSON));
			String url = String.format("%s/%s", clientProperties.getBaseUrl(),
				clientProperties.getPostOrderDeliveryDetails());
			RestaurantOrderDetails orderDetails
				= GenericBuilder.of(RestaurantOrderDetails::new)
				.with(RestaurantOrderDetails::setOrderId, restaurantOrderDetails.getOrderId())
				.with(RestaurantOrderDetails::setRestaurantId,
					restaurantOrderDetails.getRestaurantId())
				.build();
			HttpEntity<RestaurantOrderDetails> entity
				= new HttpEntity<>(orderDetails, headers);
			ResponseEntity<Response<DeliveryServiceStatus>> response =
				retryTemplate.execute(retryContext -> {
					ParameterizedTypeReference<Response<DeliveryServiceStatus>>
						parameterizedTypeReference =
						new ParameterizedTypeReference<>() {
						};
					return restTemplate.exchange(url, HttpMethod.POST, entity,
						parameterizedTypeReference);
				});
			if (!response.getStatusCode().is2xxSuccessful()) {
				throw new DeliveryFailedException(
					String.format("Failed to post delivery details for order id %s",
						restaurantOrderDetails.getOrderId()));
			}
			
			return Objects.requireNonNull(response.getBody()).getResponseBody();
		} catch (Exception ex) {
			log.error("Failed to create delivery for the order id {} ",
				restaurantOrderDetails.getOrderId());
			throw new RuntimeException(ex);
		}
	}
}

package com.example.restaurantservice.client;

import com.commons.delivery.DeliveryServiceStatus;
import com.commons.delivery.RestaurantOrderDetails;
import com.commons.utils.GenericBuilder;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
		RestaurantOrderDetails restaurantOrderDetails) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			return GenericBuilder.of(DeliveryServiceStatus::new).build();
		} catch (Exception ex) {
			log.error("Failed to create delivery for the order id {} ",
				restaurantOrderDetails.getOrderId());
			throw new RuntimeException(ex);
		}
	}
}

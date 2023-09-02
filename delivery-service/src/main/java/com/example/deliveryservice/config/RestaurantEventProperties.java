package com.example.deliveryservice.config;


import static com.example.deliveryservice.utils.Constants.RESTAURANT_PROP_PREFIX_NAME;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = RESTAURANT_PROP_PREFIX_NAME)
@Setter
@Getter
public class RestaurantEventProperties {
	
	private String deliveryInit;
	
}

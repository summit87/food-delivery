package com.example.restaurantservice.config.kafka;

import static com.example.restaurantservice.utils.Constants.PRODUCER_PROP_PREFIX_NAME;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = PRODUCER_PROP_PREFIX_NAME)
@Setter
@Getter
public class ProducerProperties {
	
	private List<String> bootstrapServers;
	private String valueSerializer;
	private String keySerializer;
	private String clientId;
	private String numberOfRetries;
	private String retriesBackoffMsConfig;
	private String maxRequestSizeConfig;
	private String acksConfig;
}

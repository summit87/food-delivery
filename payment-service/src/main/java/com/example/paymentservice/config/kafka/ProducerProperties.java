package com.example.paymentservice.config.kafka;

import static com.example.paymentservice.utils.Constants.PRODUCER_PROP_PREFIX_NAME;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = PRODUCER_PROP_PREFIX_NAME)
@Setter
@Getter
public class ProducerProperties {
	
	private String bootstrapServers;
	private String valueSerializer;
	private String keySerializer;
	private String clientId;
	private String numberOfRetries;
	private String retriesBackoffMsConfig;
	private String maxRequestSizeConfig;
	private String acksConfig;
}

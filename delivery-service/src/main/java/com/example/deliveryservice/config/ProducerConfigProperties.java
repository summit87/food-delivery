package com.example.deliveryservice.config;

import static com.example.deliveryservice.utils.Constants.PRODUCER_PROP_PREFIX_NAME;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = PRODUCER_PROP_PREFIX_NAME)
@Setter
@Getter
public class ProducerConfigProperties {
	
	private String bootstrapServers;
	private String valueSerializer;
	private String keySerializer;
	private String clientId;
	private String numberOfRetries;
	private String retriesBackoffMsConfig;
	private String maxRequestSizeConfig;
	private String acksConfig;
	private Map<String, String> topicsMap;
}

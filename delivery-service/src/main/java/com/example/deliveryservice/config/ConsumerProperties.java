package com.example.deliveryservice.config;

import static com.example.deliveryservice.utils.Constants.CONSUMER_PROP_PREFIX_NAME;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = CONSUMER_PROP_PREFIX_NAME)
@Setter
@Getter
public class ConsumerProperties {
	private List<String> bootstrapServers;
	private boolean enableAutoCommit;
	private String valueDeserializer;
	private String keyDeserializer;
	private Integer concurrency;
	private String consumerGroupId;
	private String autoOffsetResetConfig;
	private Integer maxPollRecordConfig;
	private Integer sessionTimeout;
	private Integer heartbeatInterval;
	private String clientId;
	private String deliveryInit;
}

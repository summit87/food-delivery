package com.example.paymentservice.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.paymentservice.utils.Constants.CONSUMER_PROP_PREFIX_NAME;

@Configuration
@ConfigurationProperties(prefix = CONSUMER_PROP_PREFIX_NAME)
@Setter
@Getter
public class ConsumerProperties {
	private List<String> bootstrapServers;
	private String topic;
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
}

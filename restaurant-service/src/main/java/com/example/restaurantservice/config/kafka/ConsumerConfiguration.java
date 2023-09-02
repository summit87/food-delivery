package com.example.restaurantservice.config.kafka;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.support.RetryTemplate;


@Configuration
public class ConsumerConfiguration {
	
	private final ConsumerRetriedMessageRecovery recoveryCallback;
	private final RetryTemplate kafkaRetryTemplate;
	private ConsumerProperties consumerProperties;
	
	public ConsumerConfiguration(ConsumerProperties consumerProperties,
		ConsumerRetriedMessageRecovery recoveryCallback,
		RetryTemplate kafkaRetryTemplate) {
		this.consumerProperties = consumerProperties;
		this.recoveryCallback = recoveryCallback;
		this.kafkaRetryTemplate = kafkaRetryTemplate;
	}
	
	private Map<String, Object> consumerProperties() throws IOException {
		Map<String, Object> propMapping = new HashMap<>();
		propMapping.put(BOOTSTRAP_SERVERS_CONFIG,
			consumerProperties.getBootstrapServers());
		propMapping.put(VALUE_DESERIALIZER_CLASS_CONFIG,
			consumerProperties.getValueDeserializer());
		propMapping.put(KEY_DESERIALIZER_CLASS_CONFIG,
			consumerProperties.getKeyDeserializer());
		propMapping.put(ENABLE_AUTO_COMMIT_CONFIG,
			consumerProperties.isEnableAutoCommit());
		propMapping.put(GROUP_ID_CONFIG,
			consumerProperties.getConsumerGroupId());
		propMapping.put(CLIENT_ID_CONFIG, consumerProperties.getClientId());
		propMapping.put(AUTO_OFFSET_RESET_CONFIG,
			consumerProperties.getAutoOffsetResetConfig());
		propMapping.put(MAX_POLL_RECORDS_CONFIG,
			consumerProperties.getMaxPollRecordConfig());
		propMapping.put(HEARTBEAT_INTERVAL_MS_CONFIG,
			consumerProperties.getHeartbeatInterval());
		propMapping.put(SESSION_TIMEOUT_MS_CONFIG,
			consumerProperties.getSessionTimeout());
		return propMapping;
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory()
		throws IOException {
		ConcurrentKafkaListenerContainerFactory<String, String>
			containerFactory =
			new ConcurrentKafkaListenerContainerFactory<>();
		containerFactory.setRecoveryCallback(recoveryCallback);
		containerFactory.setRetryTemplate(kafkaRetryTemplate);
		containerFactory.setConcurrency(consumerProperties.getConcurrency());
		containerFactory.setConsumerFactory(consumerFactory());
		containerFactory.getContainerProperties()
			.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return containerFactory;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory()
		throws IOException {
		
		DefaultKafkaConsumerFactory<String, Object> consumerFactory =
			new DefaultKafkaConsumerFactory<>(
				consumerProperties());
		return consumerFactory;
	}
	
}

package com.orderservice.config;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
@Slf4j
public class KafkaProducerConfig {

	private ProducerConfigProperties producerConfigProperties;

	public KafkaProducerConfig(
			ProducerConfigProperties producerConfigProperties) {
		this.producerConfigProperties = producerConfigProperties;
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaProducerTemplate() {
		log.info("Kafka producer template creation started ...");
		KafkaTemplate<String, Object> kafkaTemplate =
				new KafkaTemplate<>(producerProperties());
		log.info("Kafka producer template creation finished");
		return kafkaTemplate;
	}

	private ProducerFactory<String, Object> producerProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put(BOOTSTRAP_SERVERS_CONFIG,
				producerConfigProperties.getBootstrapServers());
		map.put(RETRIES_CONFIG, producerConfigProperties.getNumberOfRetries());
		map.put(KEY_SERIALIZER_CLASS_CONFIG,
				producerConfigProperties.getKeySerializer());
		map.put(VALUE_SERIALIZER_CLASS_CONFIG,
				producerConfigProperties.getValueSerializer());
		map.put(CLIENT_ID_CONFIG, producerConfigProperties.getClientId());
		map.put(ACKS_CONFIG, producerConfigProperties.getAcksConfig());
		return new DefaultKafkaProducerFactory<>(map);
	}
}

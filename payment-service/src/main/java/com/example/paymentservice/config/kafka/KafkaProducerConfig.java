package com.example.paymentservice.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;

@Configuration
@Slf4j
public class KafkaProducerConfig {

	private ProducerProperties producerProperties;

	public KafkaProducerConfig(ProducerProperties producerProperties) {
		this.producerProperties = producerProperties;
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
				producerProperties.getBootstrapServers());
		map.put(RETRIES_CONFIG, producerProperties.getNumberOfRetries());
		map.put(KEY_SERIALIZER_CLASS_CONFIG,
				producerProperties.getKeySerializer());
		map.put(VALUE_SERIALIZER_CLASS_CONFIG,
				producerProperties.getValueSerializer());
		map.put(CLIENT_ID_CONFIG, producerProperties.getClientId());
		map.put(ACKS_CONFIG, producerProperties.getAcksConfig());
		return new DefaultKafkaProducerFactory<>(map);
	}
}

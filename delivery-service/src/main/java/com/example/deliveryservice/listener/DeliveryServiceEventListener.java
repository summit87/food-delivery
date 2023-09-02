package com.example.deliveryservice.listener;


import com.example.deliveryservice.Adapter.ConsumerListenerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * Delivery service will consume the message from restaurant service when order are placed,
 */
@Configuration
@Slf4j
public class DeliveryServiceEventListener {
	
	private final ConsumerListenerAdapter consumerListenerAdapter;
	
	public DeliveryServiceEventListener(ConsumerListenerAdapter consumerListenerAdapter) {
		this.consumerListenerAdapter = consumerListenerAdapter;
	}
	
	@KafkaListener(topics = "#{restaurantEventProperties.deliveryInit}", containerFactory = "kafkaListenerContainerFactory")
	public void initiateDeliveryService(ConsumerRecord<String, String> record,
		Acknowledgment acknowledgment) {
		consumerListenerAdapter.consumeAndProcess(record, acknowledgment);
	}
	
}

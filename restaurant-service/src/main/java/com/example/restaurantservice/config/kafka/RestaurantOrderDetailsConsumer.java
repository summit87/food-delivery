package com.example.restaurantservice.config.kafka;

import com.example.restaurantservice.command.RestaurantCommandReceiver;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Configuration
@Slf4j
public class RestaurantOrderDetailsConsumer {
	
	private final RestaurantCommandReceiver restaurantCommandReceiver;
	
	public RestaurantOrderDetailsConsumer(
		RestaurantCommandReceiver restaurantCommandReceiver) {
		this.restaurantCommandReceiver = restaurantCommandReceiver;
	}
	
	@KafkaListener(topics = "#{consumerProperties.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void consumeRestaurantEvent(
		ConsumerRecord<String, String> consumerRecord,
		Acknowledgment acknowledgment) throws JsonProcessingException {
		
		log.info("Restaurant event consume {} , headers {} ",
			consumerRecord.value(), consumerRecord.headers());
		restaurantCommandReceiver.receiveCommand(consumerRecord,
			acknowledgment);
	}
}

package com.example.paymentservice.config.kafka;

import com.example.paymentservice.service.consumerUtils.PaymentServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Configuration
@Slf4j
public class OrderConsumerListener {
	
	private PaymentServiceUtils paymentServiceUtils;
	
	public OrderConsumerListener(PaymentServiceUtils paymentServiceUtils) {
		this.paymentServiceUtils = paymentServiceUtils;
	}
	
	@KafkaListener(topics = "#{consumerProperties.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void orderConsumerListener(
		ConsumerRecord<String, String> consumerRecord,
		Acknowledgment acknowledgment) {
		paymentServiceUtils.doOrderPayment(consumerRecord, acknowledgment);
	}
}

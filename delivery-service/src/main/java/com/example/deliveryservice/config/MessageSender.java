package com.example.deliveryservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

@Component
@Slf4j
public class MessageSender {
	
	private KafkaTemplate kafkaProducerTemplate;
	
	public MessageSender(KafkaTemplate kafkaProducerTemplate) {
		this.kafkaProducerTemplate = kafkaProducerTemplate;
	}
	
//	public <T, U> SettableListenableFuture send(T key, U value,
//		String topicName) {
//		ProducerRecord<T, U> producerRecord
//			= new ProducerRecord<>(topicName, key, value);
//		log.info("Message started to publishing into kafka for key {}",
//			key);
//		try {
//			ListenableFuture<SendResult<T, U>> send =
//				kafkaProducerTemplate.send(producerRecord);
//			final SettableListenableFuture future =
//				new SettableListenableFuture();
//			send.addCallback(new ListenableFutureCallback<SendResult<T, U>>() {
//				@Override
//				public void onFailure(Throwable ex) {
//					log.error("Failed to publish the message ", ex);
//					future.setException(ex);
//				}
//
//				@Override
//				public void onSuccess(SendResult<T, U> result) {
//					log.info("Message sent on topic {} ,partion {},offset {}",
//						result.getRecordMetadata().topic(),
//						result.getRecordMetadata().partition(),
//						result.getRecordMetadata().offset());
//					future.set(null);
//				}
//			});
//			return future;
//		} catch (Exception ex) {
//			log.error("Failed to send message to kafka for key {} ",
//				producerRecord.key());
//			throw new RuntimeException(ex);
//		}
//	}

	public <T,U> void publish(T key, U value,String topicName){
		try{
			ProducerRecord<T, U> producerRecord
					= new ProducerRecord<>(topicName, key, value);
			log.info("Message started to publishing into kafka for key {}",
					key);
			Object sendResult = kafkaProducerTemplate.send(producerRecord).get();
		}catch (Exception ex){

		}

	}
}

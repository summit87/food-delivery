package com.example.restaurantservice.config.kafka;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
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

  public <T, U> void send(T key, U value, String topicName) {
    ProducerRecord<T, U> producerRecord = new ProducerRecord<>(topicName, key, value);
    log.info("Message started to publishing into kafka for key {}", key);
    try {
      CompletableFuture<SendResult<Integer, String>> send =
          kafkaProducerTemplate.send(producerRecord);

      send.whenComplete(
          (result, exception) -> {
            if (exception == null) {
              log.info(
                  "Message send in topic {},partition {} ,offset {}",
                  result.getProducerRecord().topic(),
                  result.getProducerRecord().partition(),
                  result.getRecordMetadata().offset());
            } else {
              handleError(exception);
            }
          });
    } catch (Exception ex) {
      log.error("Failed to send message to kafka for key {} ", producerRecord.key(), ex);
      throw new RuntimeException(ex);
    }
  }

  private void handleError(Throwable exception) {
    log.error("", exception);
  }

  public <T, U> void send(
      T key, U value, String topicName, List<Header> headers) {
    ProducerRecord<T, U> producerRecord =
        new ProducerRecord<>(topicName, null, key, value, headers);
    log.info("Message started to publishing into kafka for key {}", key);
      try {
          CompletableFuture<SendResult<Integer, String>> send =
              kafkaProducerTemplate.send(producerRecord);

          send.whenComplete(
              (result, exception) -> {
                  if (exception == null) {
                      log.info(
                          "Message send in topic {},partition {} ,offset {}",
                          result.getProducerRecord().topic(),
                          result.getProducerRecord().partition(),
                          result.getRecordMetadata().offset());
                  } else {
                      handleError(exception);
                  }
              });
      } catch (Exception ex) {
          log.error("Failed to send message to kafka for key {} ", producerRecord.key(), ex);
          throw new RuntimeException(ex);
      }
  }
}

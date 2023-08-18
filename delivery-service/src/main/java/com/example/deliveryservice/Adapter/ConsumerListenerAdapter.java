package com.example.deliveryservice.Adapter;


import com.commons.model.RestaurantOrderDetails;
import com.commons.utils.GenericBuilder;
import com.example.deliveryservice.entity.DeliveryDetails;
import com.example.deliveryservice.entity.DeliveryDetailsPk;
import com.example.deliveryservice.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListenerAdapter {
  
  private final ObjectMapper objectMapper;
  private final DeliveryService deliveryServiceImpl;
  
  public ConsumerListenerAdapter(ObjectMapper objectMapper, DeliveryService deliveryServiceImpl) {
    this.objectMapper = objectMapper;
    this.deliveryServiceImpl = deliveryServiceImpl;
  }
  
  
  @Transactional(rollbackOn = Exception.class)
  public void consumeAndProcess(ConsumerRecord<String, String> consumerRecord,
      Acknowledgment acknowledgment) {
    try {
      
      RestaurantOrderDetails restaurantOrderDetails
          = objectMapper.readValue(consumerRecord.value(), RestaurantOrderDetails.class);
      log.info(
          "Message consumed from topic {} , partition {},offset {},order id {}, restaurant id {} ",
          consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
          restaurantOrderDetails.getOrderId(), restaurantOrderDetails.getRestaurantId());
      
      DeliveryDetailsPk pk = GenericBuilder.of(DeliveryDetailsPk::new)
          .with(DeliveryDetailsPk::setOrderId, restaurantOrderDetails.getOrderId())
          .with(DeliveryDetailsPk::setRestaurantId, restaurantOrderDetails.getRestaurantId())
          .build();
      DeliveryDetails deliveryDetails = GenericBuilder.of(DeliveryDetails::new)
          .with(DeliveryDetails::setDeliveryDetailsPk, pk)
          .with(DeliveryDetails::setDeliveryStatus, restaurantOrderDetails.getDeliveryStatus())
          .with(DeliveryDetails::setPaymentStatus, restaurantOrderDetails.getPaymentStatus())
          .with(DeliveryDetails::setDeliveryCharge, restaurantOrderDetails.getTotalDeliveryCharge())
          .build();
      deliveryServiceImpl.saveDelivery(deliveryDetails);
      
      log.info(
          "Message processing completed for topic {} , partition {},offset {},order id {}, restaurant id {} ",
          consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
          restaurantOrderDetails.getOrderId(), restaurantOrderDetails.getRestaurantId());
      acknowledgment.acknowledge();
    } catch (Exception exception) {
      log.error("Error while consuming message for topic {} ,partition {}, offset {} ",
          consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), exception);
      acknowledgment.acknowledge();
    }
  }
}

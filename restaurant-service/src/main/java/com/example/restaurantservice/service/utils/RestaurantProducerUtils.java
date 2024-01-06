package com.example.restaurantservice.service.utils;

import com.commons.model.RestaurantOrderDetails;
import com.example.restaurantservice.config.kafka.MessageSender;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

@Service
@Slf4j
public class RestaurantProducerUtils {

  private MessageSender messageSender;
  private ObjectMapper objectMapper;

  public RestaurantProducerUtils(MessageSender messageSender, ObjectMapper objectMapper) {
    this.messageSender = messageSender;
    this.objectMapper = objectMapper;
  }

  public void publish(RestaurantOrder order) {
    try {
      RestaurantOrderDetails restaurantOrderDetails = new RestaurantOrderDetails();
      restaurantOrderDetails.setOrderId(order.getOrderId());
      restaurantOrderDetails.setRestaurantId(order.getRestaurantId());
      restaurantOrderDetails.setRestaurantOrderStatus(order.getRestaurantOrderStatus());
      restaurantOrderDetails.setCreatedBy(order.getCreatedBy());
      restaurantOrderDetails.setPaymentStatus(order.getPaymentStatus());
      restaurantOrderDetails.setDeliveryStatus(order.getDeliveryStatus());
      restaurantOrderDetails.setCreateTs(order.getCreateTs());
      restaurantOrderDetails.setTotalDeliveryCharge(
          BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10.0, 50.0)));

      String key = String.format("%s|%s", order.getRestaurantId(), order.getOrderId());
      String value = objectMapper.writeValueAsString(restaurantOrderDetails);
      messageSender.send(key, value, Constants.INITIATE_DELIVERY);
    } catch (Exception exception) {
      log.error("Error while publishing the message to delivery service ");
    }
  }
}

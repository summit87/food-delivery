package com.example.restaurantservice.command;

import static com.example.restaurantservice.utils.Constants.EVENT_KEY_NAME;

import com.example.restaurantservice.command.service.RestaurantOrderService;
import com.example.restaurantservice.entity.RestaurantOrder;
import com.example.restaurantservice.model.RestaurantEventDetails;
import com.example.restaurantservice.service.utils.RestaurantProducerUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantCommandReceiver {

  private final RestaurantCommandFactory restaurantCommandFactory;
  private final ObjectMapper objectMapper;

  private final RestaurantProducerUtils restaurantProducerUtils;

  public RestaurantCommandReceiver(
      RestaurantCommandFactory restaurantCommandFactory,
      ObjectMapper objectMapper,
      RestaurantProducerUtils restaurantProducerUtils) {
    this.restaurantCommandFactory = restaurantCommandFactory;
    this.objectMapper = objectMapper;
    this.restaurantProducerUtils = restaurantProducerUtils;
  }

  @Transactional(rollbackOn = Exception.class)
  public void receiveCommand(
      ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment)
      throws JsonProcessingException {

    Headers headers = consumerRecord.headers();
    Map<String, String> commandNameMap = getCommandMapping(headers);
    RestaurantServiceCommandName commandName =
        RestaurantServiceCommandName.valueOf(commandNameMap.get(EVENT_KEY_NAME));

    RestaurantOrderService restaurantOrderService =
        restaurantCommandFactory.getRestaurantCommandService(commandName);
    RestaurantEventDetails restaurantEventDetails =
        objectMapper.readValue(consumerRecord.value(), RestaurantEventDetails.class);
    log.info(
        "Event {} received for the order id {} in restaurant id {} from partition {} , offset {}",
        commandName.name(),
        restaurantEventDetails.getOrderId(),
        restaurantEventDetails.getRestaurantId(),
        consumerRecord.partition(),
        consumerRecord.offset());
    RestaurantOrder order =
        restaurantOrderService.acceptEventAndUpdateOrder(restaurantEventDetails);
    acknowledgment.acknowledge();
    log.info(
        "Transaction committed for the order id {} ,restaurant id {} in partition {} , offset {}",
        order.getOrderId(),
        order.getRestaurantId(),
        consumerRecord.partition(),
        consumerRecord.offset());
    /** Publish the message to delivery service */
    log.info(
        "Start publishing the restaurant order to delivery service , restaurant id {} , order id {}",
        order.getRestaurantId(),
        order.getOrderId());
    restaurantProducerUtils.publish(order);
  }

	private Map<String, String> getCommandMapping(
			Headers headers) {
		Map<String, String> map = new HashMap<>();
		headers.iterator().forEachRemaining(header -> {
			map.put(header.key(), new String(header.value()));
		});
		return map;
	}
}

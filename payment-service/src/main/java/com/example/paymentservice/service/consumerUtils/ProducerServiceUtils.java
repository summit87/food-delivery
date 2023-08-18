package com.example.paymentservice.service.consumerUtils;

import com.commons.enums.AppEvent;
import com.commons.utils.GenericBuilder;
import com.example.paymentservice.config.kafka.MessageSender;
import com.example.paymentservice.config.kafka.producer.RestaurantMessage;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.paymentservice.utils.Constants.RESTAURANT_SERVICE;

@Service
@Slf4j
public class ProducerServiceUtils {
	private final MessageSender messageSender;
	private final ObjectMapper objectMapper;

	public ProducerServiceUtils(MessageSender messageSender,
			ObjectMapper objectMapper) {
		this.messageSender = messageSender;
		this.objectMapper = objectMapper;
	}

	public void publishEvent(PaymentStatusResponse paymentStatusResponse)
			throws JsonProcessingException {
		RestaurantMessage restaurantMessage =
				GenericBuilder.of(RestaurantMessage::new)
						.with(RestaurantMessage::setRestaurantId,
								paymentStatusResponse.getRestaurantId())
						.with(RestaurantMessage::setCustomerBillableAmount,
								paymentStatusResponse.getAmount()
										.multiply(BigDecimal.valueOf(23))
										.divide(BigDecimal.valueOf(100))
										.setScale(2, BigDecimal.ROUND_DOWN))
						.with(RestaurantMessage::setAmount,
								paymentStatusResponse.getAmount())
						.with(RestaurantMessage::setPaymentStatus,
								paymentStatusResponse.getPaymentStatus())
						.with(RestaurantMessage::setOrderId,
								paymentStatusResponse.getOrderId())
						.build();
		List<Header> headers = new ArrayList<>();
		headers.add(new RecordHeader("EVENT_NAME",
				AppEvent.RESTAURANT_ORDER_ACCEPT.name().getBytes()));
		SettableListenableFuture future =
				messageSender.send(paymentStatusResponse.getRestaurantId(),
						objectMapper.writeValueAsString(restaurantMessage),
						RESTAURANT_SERVICE,headers);
		final Object[] objects = new Object[2];
		future.addCallback(new ListenableFutureCallback() {
			@Override
			public void onFailure(Throwable ex) {
				objects[1] = ex;
				log.error(
						"Failed to send message int kafka topic for restaurant {} ",
						paymentStatusResponse.getRestaurantId(), ex);
			}

			@Override
			public void onSuccess(Object result) {
				log.info("Message publish for restaurant id {} ",
						paymentStatusResponse.getRestaurantId());
			}
		});
		if (!ObjectUtils.isEmpty(objects[1])) {
			Throwable t = (Throwable) objects[1];
			throw new RuntimeException(t.getMessage());
		}
	}
}

package com.example.paymentservice.service.consumerUtils;

import com.commons.model.PaymentRequest;
import com.example.paymentservice.model.PaymentStatusResponse;
import com.example.paymentservice.service.impl.PaymentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PaymentServiceUtils {

	private final PaymentServiceImpl paymentServiceImpl;
	private final ObjectMapper objectMapper;
	private final ProducerServiceUtils producerServiceUtils;

	public PaymentServiceUtils(PaymentServiceImpl paymentServiceImpl,
			ObjectMapper objectMapper,
			ProducerServiceUtils producerServiceUtils) {
		this.paymentServiceImpl = paymentServiceImpl;
		this.objectMapper = objectMapper;
		this.producerServiceUtils = producerServiceUtils;
	}

	@Transactional(rollbackFor = Exception.class)
	public void doOrderPayment(ConsumerRecord<String, String> consumerRecord,
			Acknowledgment acknowledgment) {
		try {

			PaymentRequest paymentRequest =
					objectMapper.readValue(consumerRecord.value(),
							PaymentRequest.class);
			log.info("Payment started for order id {} ",
					paymentRequest.getOrderId());
			PaymentStatusResponse paymentStatusResponse =
					paymentServiceImpl.savePaymentDetails(paymentRequest);
			acknowledgment.acknowledge();
			log.info("Payment completed for order id {} and transaction id {}",
					paymentStatusResponse.getOrderId(),
					paymentStatusResponse.getTransactionId());
			/**
			 * Publish the event to restaurant service
			 * Once restaurant service will receive the event ,
			 * Publish the event to respective restaurant id
			 */
			producerServiceUtils.publishEvent(paymentStatusResponse);

		} catch (JsonProcessingException e) {
			acknowledgment.acknowledge();
			log.error("", e);
		} catch (Exception ex) {
			log.error("Error while making payment ", ex);
			log.error("", ex);
		}

	}
}

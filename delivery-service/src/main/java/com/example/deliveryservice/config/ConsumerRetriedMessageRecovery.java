package com.example.deliveryservice.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;

import static org.springframework.integration.kafka.inbound.KafkaInboundEndpoint.CONTEXT_ACKNOWLEDGMENT;

@Configuration
@Qualifier("recoveryCallback")
@Slf4j
public class ConsumerRetriedMessageRecovery implements RecoveryCallback<Void> {
	
	@Override
	public Void recover(RetryContext context) throws Exception {
		log.error(
			"Recovery is called after RetryPolicy limit {}",
			context.getRetryCount());
		Acknowledgment ack =
			(Acknowledgment) context.getAttribute(CONTEXT_ACKNOWLEDGMENT);
		ack.acknowledge();
		log.error("Acknowledgement is done for payload {}", context);
		return null;
	}
}

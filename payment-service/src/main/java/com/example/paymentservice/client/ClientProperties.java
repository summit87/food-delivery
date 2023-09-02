package com.example.paymentservice.client;

import static com.example.paymentservice.utils.Constants.CLIENT_PROP_PREFIX_NAME;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = CLIENT_PROP_PREFIX_NAME)
@Setter
@Getter
public class ClientProperties {
	
	private String baseUrl;
	private String postPaymentStatusPath;
}

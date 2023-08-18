package com.orderservice.config;


import com.orderservice.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = Constants.PAYMENT_PROP_PREFIX_NAME)
@Setter
@Getter
public class PaymentServiceProperties {
	
	private String baseUrl;
	private String createPayment;
	
}

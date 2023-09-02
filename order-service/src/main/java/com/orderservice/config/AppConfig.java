package com.orderservice.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
			false);
		return objectMapper;
	}
	
	@Bean
	public RestTemplate restTemplate() throws KeyStoreException,
		NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			.loadTrustMaterial(null, acceptingTrustStrategy)
			.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
			new NoopHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
	
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		Map<Class<? extends Throwable>, Boolean> includeExceptions =
			new HashMap<>();
		includeExceptions.put(ResourceAccessException.class, true);
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(2000L);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		SimpleRetryPolicy retryPolicy =
			new SimpleRetryPolicy(5, includeExceptions, true);
		
		retryTemplate.setRetryPolicy(retryPolicy);
		return retryTemplate;
	}
}

package com.example.deliveryservice.config;


import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * - Supports both HTTP and HTTPS - Uses a connection pool to re-use connections and save overhead
 * of creating connections. - Has a custom connection keep-alive strategy (to apply a default
 * keep-alive if one isn't specified) - Starts an idle connection monitor to continuously clean up
 * stale connections.
 */
@Configuration
@EnableScheduling
public class HttpClientConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConfig.class);
	
	// Determines the timeout in milliseconds until a connection is established.
	private static final int CONNECT_TIMEOUT = 30000;
	
	// The timeout when requesting a connection from the connection manager.
	private static final int REQUEST_TIMEOUT = 30000;
	
	// The timeout for waiting for data
	private static final int SOCKET_TIMEOUT = 60000;
	
	private static final int MAX_TOTAL_CONNECTIONS = 50;
	private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;
	private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;
	
	@Bean
	public PoolingHttpClientConnectionManager poolingConnectionManager() {
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		} catch (NoSuchAlgorithmException | KeyStoreException e) {
			LOGGER.error(
				"Pooling Connection Manager Initialisation failure because of " + e.getMessage(),
				e);
		}
		
		SSLConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(builder.build());
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			LOGGER.error(
				"Pooling Connection Manager Initialisation failure because of " + e.getMessage(),
				e);
		}
		
		assert sslsf != null;
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
			.<ConnectionSocketFactory>create().register("https", sslsf)
			.register("http", new PlainConnectionSocketFactory())
			.build();
		
		PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(
			socketFactoryRegistry);
		poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		return poolingConnectionManager;
	}
	
	@Bean
	public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
		return (response, context) -> {
			Iterator<Header> headerIterator = response.headerIterator(HTTP.CONN_KEEP_ALIVE);
			BasicHeaderElementIterator it = new BasicHeaderElementIterator(headerIterator);
			while (it.hasNext()) {
				HeaderElement he = it.next();
				String param = he.getName();
				String value = he.getValue();
				
				if (value != null && param.equalsIgnoreCase("timeout")) {
					return TimeValue.ofDays(Long.parseLong(value) * 1000);
				}
			}
			return TimeValue.ofDays(DEFAULT_KEEP_ALIVE_TIME_MILLIS);
		};
	}
	
	@Bean
	public CloseableHttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
			.setConnectionRequestTimeout(Timeout.ofDays(REQUEST_TIMEOUT))
			
			.build();
		
		return HttpClients.custom()
			.setDefaultRequestConfig(requestConfig)
			.setConnectionManager(poolingConnectionManager())
			.setKeepAliveStrategy(connectionKeepAliveStrategy())
			.build();
	}
	
	/*@Bean
	public Runnable idleConnectionMonitor(
		final PoolingHttpClientConnectionManager connectionManager) {
		return new Runnable() {
			@Override
			@Scheduled(fixedDelay = 10000)
			public void run() {
				try {
					if (connectionManager != null) {
						LOGGER.trace(
							"run IdleConnectionMonitor - Closing expired and idle connections...");
						connectionManager.close();
						connectionManager.closeIdle(
							TimeValue.ofDays(CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS));
					} else {
						LOGGER.trace(
							"run IdleConnectionMonitor - Http Client Connection manager is not initialised");
					}
				} catch (Exception e) {
					LOGGER.error("run IdleConnectionMonitor - Exception occurred. msg={}, e={}",
						e.getMessage(), e);
				}
			}
		};
	}*/
}

package com.notification;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class NotificationServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
}


@RestController
class NotificationServiceHealth {
	
	@GetMapping(value = "/health")
	public Mono<ResponseEntity<Map<String, String>>> healthStatus() {
		Map<String, String> healthMa = new HashMap<>();
		healthMa.put("status","Running");
		return Mono.just(new ResponseEntity<Map<String, String>>(healthMa, HttpStatus.OK));
	}
}
package com.example.restaurantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}

}

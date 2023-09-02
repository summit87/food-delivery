package com.example.restaurantservice.exception;

public class OrderNotFoundException extends RuntimeException {
	
	public OrderNotFoundException(String message) {
		super(message);
	}
}

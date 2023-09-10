package com.example.restaurantservice.exception;

public class DeliveryFailedException extends RuntimeException {
	
	public DeliveryFailedException(String message) {
		super(message);
	}
}

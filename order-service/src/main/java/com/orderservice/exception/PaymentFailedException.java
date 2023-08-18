package com.orderservice.exception;

public class PaymentFailedException extends RuntimeException{
	
	public PaymentFailedException(String s) {
		super(s);
	}
}

package com.example.paymentservice.exception;



public class PaymentFailedException extends RuntimeException{
	
	public PaymentFailedException(String s) {
		super(s);
	}
}

package com.customer.exception;

public class CustomerDuplicateRecord extends RuntimeException{
	
	public CustomerDuplicateRecord(String message) {
		super(message);
	}
}

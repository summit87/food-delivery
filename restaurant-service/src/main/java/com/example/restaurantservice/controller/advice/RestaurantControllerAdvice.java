package com.example.restaurantservice.controller.advice;

import com.commons.enums.Response;
import com.commons.response.ApiErrorResponse;
import com.commons.utils.GenericBuilder;
import com.example.restaurantservice.exception.DeliveryFailedException;
import com.example.restaurantservice.exception.OrderNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class RestaurantControllerAdvice {
	
	@ExceptionHandler(value = {OrderNotFoundException.class})
	
	public ResponseEntity<Response<ApiErrorResponse>> handleAllException(Exception ex,
		WebRequest request) throws JsonProcessingException {
		
		ApiErrorResponse apiResponse = GenericBuilder
			.of(ApiErrorResponse::new)
			.with(ApiErrorResponse::setErrorType, ex.getClass().getName())
			.with(ApiErrorResponse::setErrorMessage, ex.getMessage())
			.with(ApiErrorResponse::setErrorCode, "OS.GET.0001")
			.build();
		Response<ApiErrorResponse> response =
			new Response.ResponseBuilder<ApiErrorResponse>()
				.responseBody(apiResponse)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Response<ApiErrorResponse>> handleAllException5XX(Exception ex,
		WebRequest request) throws JsonProcessingException {
		
		ApiErrorResponse apiResponse = GenericBuilder
			.of(ApiErrorResponse::new)
			.with(ApiErrorResponse::setErrorType, ex.getClass().getName())
			.with(ApiErrorResponse::setErrorMessage, ex.getMessage())
			.with(ApiErrorResponse::setErrorCode, "OS.GET.0002")
			.build();
		Response<ApiErrorResponse> response =
			new Response.ResponseBuilder<ApiErrorResponse>()
				.responseBody(apiResponse)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {DeliveryFailedException.class})
	
	public ResponseEntity<Response<ApiErrorResponse>> handleDeliveryException(Exception ex,
		WebRequest request) throws JsonProcessingException {
		
		ApiErrorResponse apiResponse = GenericBuilder
			.of(ApiErrorResponse::new)
			.with(ApiErrorResponse::setErrorType, ex.getClass().getName())
			.with(ApiErrorResponse::setErrorMessage, ex.getMessage())
			.with(ApiErrorResponse::setErrorCode, "DELIVERY.POST.0001")
			.build();
		Response<ApiErrorResponse> response =
			new Response.ResponseBuilder<ApiErrorResponse>()
				.responseBody(apiResponse)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}

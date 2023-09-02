package com.orderservice.controller.advice;

import com.commons.enums.Response;
import com.commons.response.ApiErrorResponse;
import com.commons.utils.GenericBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderservice.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class OrderServiceRestControllerAdvice extends
	ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {OrderNotFoundException.class})
	
	public ResponseEntity<Response> handleAllException(Exception ex,
		WebRequest request) throws JsonProcessingException {
		
		ApiErrorResponse apiResponse = GenericBuilder
			.of(ApiErrorResponse::new)
			.with(ApiErrorResponse::setErrorType, ex.getClass().getName())
			.with(ApiErrorResponse::setErrorMessage, ex.getMessage())
			.with(ApiErrorResponse::setErrorCode, "OS.GET.0001")
			.build();
		Response response =
			new Response.ResponseBuilder<ApiErrorResponse>()
				.responseBody(apiResponse)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Response> handleAllException5XX(Exception ex,
		WebRequest request) throws JsonProcessingException {
		
		ApiErrorResponse apiResponse = GenericBuilder
			.of(ApiErrorResponse::new)
			.with(ApiErrorResponse::setErrorType, ex.getClass().getName())
			.with(ApiErrorResponse::setErrorMessage, ex.getMessage())
			.with(ApiErrorResponse::setErrorCode, "OS.GET.0002")
			.build();
		Response response =
			new Response.ResponseBuilder()
				.responseBody(apiResponse)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

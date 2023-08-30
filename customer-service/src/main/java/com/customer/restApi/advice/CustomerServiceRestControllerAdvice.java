package com.customer.restApi.advice;

import com.commons.enums.Response;
import com.commons.enums.Response.ResponseBuilder;
import com.commons.response.ApiErrorResponse;
import com.commons.utils.GenericBuilder;
import com.customer.exception.CustomerDuplicateRecord;
import com.customer.exception.CustomerNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomerServiceRestControllerAdvice extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerNotFoundException.class})

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
                new ResponseBuilder<ApiErrorResponse>()
                        .responseBody(apiResponse)
                        .build();
        log.error("", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = {CustomerDuplicateRecord.class})
    public ResponseEntity<Response<ApiErrorResponse>> handleAllExceptionDataIntegrityViolationException(Exception ex,
        WebRequest request) throws JsonProcessingException {
        
        ApiErrorResponse apiResponse = GenericBuilder
            .of(ApiErrorResponse::new)
            .with(ApiErrorResponse::setErrorType, ex.getClass().getName())
            .with(ApiErrorResponse::setErrorMessage, ex.getMessage())
            .with(ApiErrorResponse::setErrorCode, "CS.PROFILE.0003")
            .build();
        Response<ApiErrorResponse> response =
            new Response.ResponseBuilder<ApiErrorResponse>()
                .responseBody(apiResponse)
                .build();
        log.error("", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.vinod.validation.exception.exception;

import com.vinod.validation.exception.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Response.builder().status(HttpStatus.BAD_REQUEST.value()).errorStatus(HttpStatus.EXPECTATION_FAILED.toString()).errorReason("Method arguments are invalid").errorCause(ex.getMessage()).build(),HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Response.builder().status(HttpStatus.BAD_REQUEST.value()).errorStatus(HttpStatus.EXPECTATION_FAILED.toString()).errorReason("Request Http method not allowed.").errorCause(ex.getMessage()).build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNameNotFoundException.class)
    protected ResponseEntity<Object> handleCustomerNameNotFoundException(CustomerNameNotFoundException ex, WebRequest request) {
        log.warn("Unable to get the customer details, error msg: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(Response.builder().status(HttpStatus.NOT_FOUND.value()).errorStatus(HttpStatus.NOT_FOUND.toString()).errorReason("Sorry! Customer not found.").errorCause(ex.getMessage()).build(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.warn("Unable to get the customer details, error msg: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(Response.builder().status(HttpStatus.BAD_REQUEST.value()).errorStatus(HttpStatus.BAD_REQUEST.toString()).errorReason("Method arguments are invalid..... ").errorCause(ex.getMessage()).build(),HttpStatus.BAD_REQUEST);
    }
}

package com.test.reward_calculator_api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(
            CustomerNotFoundException exception) {

        LOGGER.error(exception.getMessage());

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(
            Exception exception) {

        LOGGER.error(exception.getMessage());

        return new ResponseEntity<>(
                "Something went wrong. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(
            javax.validation.ConstraintViolationException exception) {

        LOGGER.error(exception.getMessage());

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
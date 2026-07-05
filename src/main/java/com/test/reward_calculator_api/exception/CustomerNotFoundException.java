package com.test.reward_calculator_api.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Customer not found with id : " + customerId);
    }

}
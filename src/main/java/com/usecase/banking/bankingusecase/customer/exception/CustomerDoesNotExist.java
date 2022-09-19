package com.usecase.banking.bankingusecase.customer.exception;

public class CustomerDoesNotExist extends Exception{

    public CustomerDoesNotExist(String message) {
        super(message);
    }
}

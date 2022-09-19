package com.usecase.banking.bankingusecase.account.exception;

public class InsufficientBalance extends Exception {
    public InsufficientBalance(String message) {
        super(message);
    }
}

package com.usecase.banking.bankingusecase.account.exception;

public class AccountDoesNotExist extends Exception {
    public AccountDoesNotExist(String message) {
        super(message);
    }
}
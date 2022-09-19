package com.usecase.banking.bankingusecase.bank.exception;

public class BankDoesNotExist extends Exception {
    public BankDoesNotExist(String message) {
        super(message);
    }
}

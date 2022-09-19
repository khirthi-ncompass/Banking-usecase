package com.usecase.banking.bankingusecase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    MISSING_CUSTOMER_ID("Missing customerId. Please enter  customerId."),
    MISSING_IFSC_CODE("Missing ifscCode. Please enter  ifscCode."),
    MISSING_BANK_NAME("Missing bankName. Please enter  bankName."),
    MISSING_CUSTOMER_NAME("Missing customerName. Please enter  customerName."),
    MISSING_PHONE_NUMBER("Missing phoneNumber. Please enter  phoneNumber."),
    MISSING_ACCOUNT_NUMBER("Missing accountNumber. Please enter  accountNumber."),
    MISSING_ADDRESS("Missing address. Please enter address."),
    MISSING_ACCOUNT_TYPE("Missing accountType. Please enter  accountType."),
    MISSING_CREDIT_AMOUNT("Enter creditAmount."),
    ENTER_VALID_AMOUNT("Please enter valid creditAmount."),
    MISSING_DEBIT_AMOUNT("Enter debitAmount."),
    INSUFFICIENT_BALANCE("Insufficient balance. Enter valid debitAmount.");

    private String errorMessage;
}

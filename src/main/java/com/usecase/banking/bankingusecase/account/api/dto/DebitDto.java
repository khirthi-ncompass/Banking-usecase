package com.usecase.banking.bankingusecase.account.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DebitDto {

    @NotNull(message = "Enter Debit Amount")
    private double debitAmount;

    @NotNull(message = "Enter accountNumber")
    private String accountNumber;
}

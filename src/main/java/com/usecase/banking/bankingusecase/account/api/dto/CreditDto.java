package com.usecase.banking.bankingusecase.account.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreditDto {

    @NotNull(message = "Credit Amount is null")
    private double creditAmount;

    @NotNull(message = "Enter accountNNumber")
    private String accountNumber;
}

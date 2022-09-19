package com.usecase.banking.bankingusecase.account.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountOutputDto {

    @NotNull(message = "CustomerId cannot be null")
    private String customerId;

    @NotNull(message = "Bank Ifsc Code cannot be blank")
    private String ifscCode;

    @NotNull(message = "Account Type code cannot be blank")
    private String accountType;

}

package com.usecase.banking.bankingusecase.bank.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddBankDto {

    @NotNull(message = "Bank Name cannot be blank")
    private String name;

    @NotNull(message = "Address cannot be blank")
    private String address;

    @NotNull(message = "IFSC code cannot be blank")
    private String ifscCode;
}


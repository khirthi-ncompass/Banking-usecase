package com.usecase.banking.bankingusecase.customer.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddCustomerDto {

    @NotNull(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "PhoneNumber cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Address cannot be blank")
    private String address;
}


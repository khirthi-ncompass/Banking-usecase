package com.usecase.banking.bankingusecase.customer.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateCustomerDto{

    @NotNull(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Address cannot be blank")
    private String address;

    @NotNull(message = "PhoneNumber cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Customer id cannot be null")
    String id;
}

package com.usecase.banking.bankingusecase.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountNumber;

    private  String customerId;

    private  String ifscCode;

    private String accountType;

    private  double balance;
}

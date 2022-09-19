package com.usecase.banking.bankingusecase.account.service;


import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountOutputDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreditDto;
import com.usecase.banking.bankingusecase.account.api.dto.DebitDto;
import com.usecase.banking.bankingusecase.account.model.Account;
import com.usecase.banking.bankingusecase.account.exception.AccountDoesNotExist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    CreateAccountOutputDto addAccount(CreateAccountDto newAccount) throws Exception;
    String removeAccount(String accountNumber) throws AccountDoesNotExist;

    List<Account> getAllAccounts();
    Account getAccountDetailsUsingAccNum(String accountNumber) throws AccountDoesNotExist;
    void debit(DebitDto debitDto) throws Exception;
    void credit(CreditDto creditDto) throws AccountDoesNotExist;

}

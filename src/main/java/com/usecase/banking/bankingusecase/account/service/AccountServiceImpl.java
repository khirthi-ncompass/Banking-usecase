package com.usecase.banking.bankingusecase.account.service;


import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountOutputDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreditDto;
import com.usecase.banking.bankingusecase.account.api.dto.DebitDto;
import com.usecase.banking.bankingusecase.account.model.Account;
import com.usecase.banking.bankingusecase.account.exception.AccountDoesNotExist;
import com.usecase.banking.bankingusecase.account.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public CreateAccountOutputDto addAccount(CreateAccountDto newAccount) throws Exception {

        Account account = new Account();
        BeanUtils.copyProperties(newAccount, account);

        String publicAccountNumber = AccNumGenerator.generateCustomerId(6);
        account.setAccountNumber("ACCNUM" + publicAccountNumber);

        if(newAccount.getAccountType().equals("SAVINGS_ACCOUNT")) {
            account.setAccountType("SAVINGS_ACCOUNT");
            account.setBalance(1000);
        }
        else if (newAccount.getAccountType().equals("CURRENT_ACCOUNT")) {
            account.setAccountType("CURRENT_ACCOUNT");
            account.setBalance(0);
        }
        else {
            throw new Exception("Enter valid account type");
        }

        CreateAccountDto finalAccountDto = new CreateAccountDto();
        BeanUtils.copyProperties(account, finalAccountDto);

        this.accountRepository.addAccount(finalAccountDto);
        //add success/failure message

        CreateAccountOutputDto finalAccount = new CreateAccountOutputDto();
        BeanUtils.copyProperties(finalAccountDto, finalAccount);

        return finalAccount;
    }

    @Override
    public String removeAccount(String accountNumber) throws AccountDoesNotExist {
        this.accountRepository.removeAccount(accountNumber);
        return ("Deletion of accountNumber "+accountNumber+" is Successful");
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.getAllAccounts();
    }

    @Override
    public Account getAccountDetailsUsingAccNum(String accountNumber) throws AccountDoesNotExist {
        return this.accountRepository.getAccountDetailsUsingAccNum(accountNumber);
    }

    @Override
    public void debit(DebitDto debitDto) throws Exception {
        this.accountRepository.debit(debitDto);
    }

    @Override
    public void credit(CreditDto creditDto) throws AccountDoesNotExist {
        this.accountRepository.credit(creditDto);
    }
}

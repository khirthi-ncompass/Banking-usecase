package com.usecase.banking.bankingusecase.account.repository;


import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreditDto;
import com.usecase.banking.bankingusecase.account.api.dto.DebitDto;
import com.usecase.banking.bankingusecase.account.model.Account;
import com.usecase.banking.bankingusecase.account.exception.AccountDoesNotExist;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AccountRepository {

    void addAccount(CreateAccountDto newAccount);
    void removeAccount(String accountNumber) throws AccountDoesNotExist;

    List<Account> getAllAccounts();
    Account getAccountDetailsUsingAccNum(String accountNumber) throws AccountDoesNotExist;
    void debit(DebitDto debitdto) throws Exception;
    void credit(CreditDto creditDto) throws AccountDoesNotExist;

}

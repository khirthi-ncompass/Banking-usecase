package com.usecase.banking.bankingusecase.account.repository;

import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreditDto;
import com.usecase.banking.bankingusecase.account.api.dto.DebitDto;
import com.usecase.banking.bankingusecase.account.model.Account;
import com.usecase.banking.bankingusecase.account.exception.AccountDoesNotExist;
import com.usecase.banking.bankingusecase.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ACCOUNTS_INFO = "SELECT * FROM ACCOUNT";

    private static final String GET_ACCOUNT_INFO_USING_ACCOUNT_NUMBER = "SELECT * FROM ACCOUNT WHERE accountNumber =?";

    private static final String ADD_ACCOUNT_INFO = "INSERT INTO ACCOUNT (accountNumber, customerId, ifscCode, accountType, balance) VALUES (?,?,?,?,?)";

    private static final String DELETE_ACCOUNT_USING_ACCOUNT_NUMBER = "DELETE FROM ACCOUNT WHERE accountNumber =?";

    private static final String CREDIT_INTO_ACCOUNT = "UPDATE ACCOUNT SET balance = ? where accountNumber = ?";

    private static final String DEBIT_INTO_ACCOUNT = "UPDATE ACCOUNT SET balance = ? where accountNumber = ?";

    private final BeanPropertyRowMapper<Account> accountBeanRowMapper =
            new BeanPropertyRowMapper<>(Account.class);


    @Override
    public void addAccount(CreateAccountDto newAccount) {
        this.jdbcTemplate.update(ADD_ACCOUNT_INFO, new Object[]{
                newAccount.getAccountNumber(),
                newAccount.getCustomerId(),
                newAccount.getIfscCode(),
                newAccount.getAccountType(),
                newAccount.getBalance()
        });
    }

    @Override
    public Account getAccountDetailsUsingAccNum(String accountNumber) throws AccountDoesNotExist {
        Account account;
        try{
            account = this.jdbcTemplate.queryForObject(GET_ACCOUNT_INFO_USING_ACCOUNT_NUMBER, new Object[]{accountNumber}, this.accountBeanRowMapper);
        }catch (EmptyResultDataAccessException e){
            account = null;
        }
        if (account == null) {
            throw new AccountDoesNotExist("Account with accountNumber "+accountNumber+" does not exist");
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.jdbcTemplate.query(GET_ALL_ACCOUNTS_INFO, this.accountBeanRowMapper);
    }

    @Override
    public void removeAccount(String accountNumber) throws AccountDoesNotExist {
        Account account = this.getAccountDetailsUsingAccNum(accountNumber);
        this.jdbcTemplate.update(DELETE_ACCOUNT_USING_ACCOUNT_NUMBER, new Object[]{accountNumber});
    }

    @Override
    public void credit(CreditDto creditDto) throws AccountDoesNotExist {
        double amount = creditDto.getCreditAmount();
        String customerId = creditDto.getAccountNumber();

        Account account = this.getAccountDetailsUsingAccNum(creditDto.getAccountNumber());
        double currentBalance = account.getBalance();

        currentBalance += amount;
        account.setBalance(currentBalance);

        this.jdbcTemplate.update(CREDIT_INTO_ACCOUNT, currentBalance, customerId);
    }

    @Override
    public void debit(DebitDto debitDto) throws Exception {
        double amount = debitDto.getDebitAmount();
        String customerId = debitDto.getAccountNumber();

        Account account = this.getAccountDetailsUsingAccNum(debitDto.getAccountNumber());
        double currentBalance = account.getBalance();

        currentBalance -= amount;
        if(currentBalance < 0) {
            throw new Exception(ErrorMessages.INSUFFICIENT_BALANCE.getErrorMessage());
        }
        else {
            account.setBalance(currentBalance);
        }
        this.jdbcTemplate.update(DEBIT_INTO_ACCOUNT, currentBalance, customerId);
    }
}

package com.usecase.banking.bankingusecase.account.api;

import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreateAccountOutputDto;
import com.usecase.banking.bankingusecase.account.api.dto.CreditDto;
import com.usecase.banking.bankingusecase.account.api.dto.DebitDto;
import com.usecase.banking.bankingusecase.account.service.AccountService;
import com.usecase.banking.bankingusecase.account.model.Account;
import com.usecase.banking.bankingusecase.account.exception.AccountDoesNotExist;

import com.usecase.banking.bankingusecase.exception.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("create-account")
    ResponseEntity<CreateAccountOutputDto> createNewAccount(@Valid @RequestBody CreateAccountDto newAccount) throws Exception {

        if(newAccount.getCustomerId().isEmpty()) throw new Exception(ErrorMessages.MISSING_CUSTOMER_ID.getErrorMessage());
        if(newAccount.getIfscCode().isEmpty()) throw new Exception(ErrorMessages.MISSING_IFSC_CODE.getErrorMessage());
        if(newAccount.getAccountType().isEmpty()) throw new Exception(ErrorMessages.MISSING_ACCOUNT_TYPE.getErrorMessage());


        this.accountService.addAccount(newAccount);
        CreateAccountOutputDto finalOutput = new CreateAccountOutputDto();
        BeanUtils.copyProperties(newAccount, finalOutput);

        return new ResponseEntity<>(finalOutput, HttpStatus.OK);
    }

    @GetMapping("get-all")
    ResponseEntity<List<Account>> getAllCustomers(){
        List<Account> accountList = this.accountService.getAllAccounts();
        return new ResponseEntity<>(accountList,HttpStatus.OK);
    }

    @GetMapping("id/{accountNumber}")
    ResponseEntity<Account> getAccountByAccNum(@Valid @PathVariable String accountNumber) throws AccountDoesNotExist {
        Account account = this.accountService.getAccountDetailsUsingAccNum(accountNumber);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @PostMapping("credit")
    ResponseEntity<?> creditAmount(@Valid @RequestBody CreditDto creditDto) throws Exception {

        if(creditDto.getAccountNumber().isEmpty()) throw new Exception(ErrorMessages.MISSING_ACCOUNT_NUMBER.getErrorMessage());
        if(creditDto.getCreditAmount() <= 0) throw new Exception(ErrorMessages.ENTER_VALID_AMOUNT.getErrorMessage());

        this.accountService.credit(creditDto);
        return new ResponseEntity<>(creditDto, HttpStatus.OK);
    }

    @PostMapping("debit")
    ResponseEntity<?> debitAmount(@Valid @RequestBody DebitDto debitDto) throws Exception {

        if(debitDto.getAccountNumber().isEmpty()) throw new Exception(ErrorMessages.MISSING_ACCOUNT_NUMBER.getErrorMessage());
        if(debitDto.getDebitAmount() <= 0) throw new Exception(ErrorMessages.ENTER_VALID_AMOUNT.getErrorMessage());

        this.accountService.debit(debitDto);
        return new ResponseEntity<>(debitDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/id/{accountNumber}")
    ResponseEntity<String> deleteCustomerById(@Valid @PathVariable String accountNumber) throws Exception {
        String account = this.accountService.removeAccount(accountNumber);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }
}

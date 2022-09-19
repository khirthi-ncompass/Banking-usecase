package com.usecase.banking.bankingusecase.bank.service;


import com.usecase.banking.bankingusecase.bank.model.Bank;
import com.usecase.banking.bankingusecase.bank.exception.BankDoesNotExist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    Bank addBankInfo(Bank bank);

    List<Bank> getAllBankInfo();

    public Bank getBankInfoByIfscCode(String ifscCode) throws BankDoesNotExist;

    String deleteBankByIfscCode(String ifscCode) throws BankDoesNotExist, Exception;
}

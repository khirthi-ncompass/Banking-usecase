package com.usecase.banking.bankingusecase.bank.repository;


import com.usecase.banking.bankingusecase.bank.model.Bank;
import com.usecase.banking.bankingusecase.bank.exception.BankDoesNotExist;



import java.util.List;

public interface BankRepository {

    Bank addBankInfo(Bank bank);

    List<Bank> getAllBankInfo();

    public Bank getBankInfoByIfscCode(String ifscCode) throws BankDoesNotExist;

    void deleteBankByIfscCode(String ifscCode) throws BankDoesNotExist;
}

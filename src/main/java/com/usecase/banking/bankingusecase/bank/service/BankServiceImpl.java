package com.usecase.banking.bankingusecase.bank.service;


import com.usecase.banking.bankingusecase.bank.model.Bank;
import com.usecase.banking.bankingusecase.bank.exception.BankDoesNotExist;
import com.usecase.banking.bankingusecase.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Bank addBankInfo(Bank bank) {
        return bankRepository.addBankInfo(bank);
    }

    @Override
    public Bank getBankInfoByIfscCode(String ifscCode) throws BankDoesNotExist {
        return this.bankRepository.getBankInfoByIfscCode(ifscCode);
    }

    @Override
    public List<Bank> getAllBankInfo() {
        return this.bankRepository.getAllBankInfo();
    }

    @Override
    public String deleteBankByIfscCode(String ifscCode) throws Exception {
        this.bankRepository.deleteBankByIfscCode(ifscCode);
        return ("Deletion of bank with ifscCode "+ifscCode+" is successful");
    }
}

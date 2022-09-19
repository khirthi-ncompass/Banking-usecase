package com.usecase.banking.bankingusecase.bank.api;

import com.usecase.banking.bankingusecase.bank.api.dto.AddBankDto;
import com.usecase.banking.bankingusecase.bank.service.BankService;
import com.usecase.banking.bankingusecase.bank.model.Bank;

import com.usecase.banking.bankingusecase.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("get-all")
    ResponseEntity<List<Bank>> getAllBankInfo(){
        List<Bank> bankList = this.bankService.getAllBankInfo();
        return new ResponseEntity<>(bankList,HttpStatus.OK);
    }

    @GetMapping("id/{ifscCode}")
    ResponseEntity<Bank> getBankInfoByIfscCode(@PathVariable String ifscCode) throws Exception {
        Bank bank = this.bankService.getBankInfoByIfscCode(ifscCode);
        return new ResponseEntity<>(bank,HttpStatus.OK);
    }

    @PostMapping("addBank")
    ResponseEntity<?> addBankInfo(@Valid @RequestBody AddBankDto newBank) throws Exception {

        if(newBank.getName().isEmpty()) throw new Exception(ErrorMessages.MISSING_BANK_NAME.getErrorMessage());
        if(newBank.getAddress().isEmpty()) throw new Exception(ErrorMessages.MISSING_ADDRESS.getErrorMessage());
        if(newBank.getIfscCode().isEmpty()) throw new Exception(ErrorMessages.MISSING_IFSC_CODE.getErrorMessage());

        Bank bank = new Bank(newBank.getName(), newBank.getAddress(), newBank.getIfscCode());
        this.bankService.addBankInfo(bank);
        return new ResponseEntity<>(newBank,HttpStatus.OK);
    }

    @DeleteMapping("delete/id/{ifscCode}")
    ResponseEntity<?> deleteBankByIfscCode(@PathVariable String ifscCode) throws Exception {
        String bank = this.bankService.deleteBankByIfscCode(ifscCode);
        return new ResponseEntity<>(bank,HttpStatus.OK);
    }

}

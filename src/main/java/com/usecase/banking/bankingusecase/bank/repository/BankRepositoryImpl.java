package com.usecase.banking.bankingusecase.bank.repository;

import com.usecase.banking.bankingusecase.bank.model.Bank;
import com.usecase.banking.bankingusecase.bank.exception.BankDoesNotExist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class BankRepositoryImpl implements BankRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_BANK_INFO = "SELECT * FROM BANK";

    private static final String GET_BANK_INFO_BY_ID = "SELECT * FROM BANK WHERE ifscCode =?";

    private static final String ADD_BANK_INFO = "INSERT INTO BANK (name, address, ifscCode) VALUES (?, ?, ?)";

    private static final String DELETE_BANK = "DELETE FROM BANK WHERE ifscCode =?";

    private final BeanPropertyRowMapper<Bank> bankBeanRowMapper =
            new BeanPropertyRowMapper<>(Bank.class);

    @Override
    public Bank addBankInfo(Bank bank) {
        this.jdbcTemplate.update(ADD_BANK_INFO, new Object[]{bank.getName(), bank.getAddress(), bank.getIfscCode()});
        return bank;
    }

    @Override
    public Bank getBankInfoByIfscCode(String ifscCode) throws BankDoesNotExist {
        Bank bank;
        try{
            bank = this.jdbcTemplate.queryForObject(GET_BANK_INFO_BY_ID, new Object[]{ifscCode}, this.bankBeanRowMapper);
        }catch (EmptyResultDataAccessException e){
            bank = null;
        }
        if (bank == null) {
            throw new BankDoesNotExist("Bank with ifscCode "+ifscCode+" does not exist");
        }
        return bank;
    }

    @Override
    public List<Bank> getAllBankInfo() {
        return this.jdbcTemplate.query(GET_BANK_INFO, this.bankBeanRowMapper);
    }

    @Override
    public void deleteBankByIfscCode(String ifscCode) throws BankDoesNotExist {
        Bank bank = this.getBankInfoByIfscCode(ifscCode);
        this.jdbcTemplate.update(DELETE_BANK, new Object[]{ifscCode});
    }
}

package com.usecase.banking.bankingusecase.customer.service;


import com.usecase.banking.bankingusecase.customer.model.Customer;
import com.usecase.banking.bankingusecase.customer.exception.CustomerDoesNotExist;
import com.usecase.banking.bankingusecase.customer.exception.EmptyFieldException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Customer addCustomer(Customer customer) throws EmptyFieldException;

    Customer getCustomerById(String id) throws CustomerDoesNotExist;

    List<Customer> getAllCustomer();

    Customer updateCustomer(Customer customer) throws CustomerDoesNotExist;

    String deleteCustomerById(String id) throws CustomerDoesNotExist;
}

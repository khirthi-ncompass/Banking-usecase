package com.usecase.banking.bankingusecase.customer.repository;


import com.usecase.banking.bankingusecase.customer.model.Customer;
import com.usecase.banking.bankingusecase.customer.exception.CustomerDoesNotExist;
import com.usecase.banking.bankingusecase.customer.exception.EmptyFieldException;

import java.util.List;

public interface CustomerRepository {

    Customer addCustomer(Customer customer) throws EmptyFieldException;

    Customer getCustomerById(String id) throws CustomerDoesNotExist;

    List<Customer> getAllCustomer();

    Customer updateCustomerById(Customer customer) throws CustomerDoesNotExist;

    Customer deleteCustomerById(String id) throws CustomerDoesNotExist;
}

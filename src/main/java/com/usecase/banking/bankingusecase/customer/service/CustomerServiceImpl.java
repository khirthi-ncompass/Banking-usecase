package com.usecase.banking.bankingusecase.customer.service;


import com.usecase.banking.bankingusecase.customer.model.Customer;
import com.usecase.banking.bankingusecase.customer.exception.CustomerDoesNotExist;
import com.usecase.banking.bankingusecase.customer.exception.EmptyFieldException;
import com.usecase.banking.bankingusecase.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) throws EmptyFieldException {
        return customerRepository.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerDoesNotExist {
        return this.customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomer()
    {
        return this.customerRepository.getAllCustomer();
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerDoesNotExist {
        return this.customerRepository.updateCustomerById(customer);
    }

    @Override
    public String deleteCustomerById(String id) throws CustomerDoesNotExist {
        this.customerRepository.deleteCustomerById(id);
        return ("Deletion of customer with customerId "+id+" is successful");
    }
}

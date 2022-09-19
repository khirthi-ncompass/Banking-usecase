package com.usecase.banking.bankingusecase.customer.repository;

import com.usecase.banking.bankingusecase.customer.model.Customer;
import com.usecase.banking.bankingusecase.customer.exception.CustomerDoesNotExist;
import com.usecase.banking.bankingusecase.customer.exception.EmptyFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class CustomerRepositoryImpl implements  CustomerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_CUSTOMER = "SELECT * FROM CUSTOMER WHERE id =?";

    private static final String GET_ALL_CUSTOMER = "SELECT * FROM CUSTOMER";

    private static final String ADD_CUSTOMER = "INSERT INTO CUSTOMER (name, address, phoneNumber) VALUES (?, ?, ?)";

    private static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE id = ?";

    private static final String UPDATE_CUSTOMER = "UPDATE customer SET name = ?, address = ?, phoneNumber = ? WHERE id =?";

    private final BeanPropertyRowMapper<Customer> customerBeanRowMapper =
            new BeanPropertyRowMapper<>(Customer.class);

    @Override
    public Customer addCustomer(Customer customer) throws EmptyFieldException {
                this.jdbcTemplate.update(ADD_CUSTOMER, new Object[]{
                        customer.getName(),
                        customer.getPhoneNumber(),
                        customer.getAddress()
                });
        return customer;
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerDoesNotExist {
        Customer customer;
        try{
            customer = this.jdbcTemplate.queryForObject(GET_CUSTOMER, new Object[]{id}, this.customerBeanRowMapper);
        }catch (EmptyResultDataAccessException e){
            customer = null;
        }
        if (customer == null) {
            throw new CustomerDoesNotExist("Customer with id "+id+" does not exist");
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return this.jdbcTemplate.query(GET_ALL_CUSTOMER, this.customerBeanRowMapper);
    }

    @Override
    public Customer updateCustomerById(Customer updatedCustomer) throws CustomerDoesNotExist{
        Customer customer = this.getCustomerById(updatedCustomer.getId());
        this.jdbcTemplate.update(UPDATE_CUSTOMER, new Object[]{updatedCustomer.getName(), updatedCustomer.getAddress(),
                updatedCustomer.getPhoneNumber(), updatedCustomer.getId()});
        return customer;
    }

    @Override
    public Customer deleteCustomerById(String id) throws CustomerDoesNotExist {
        Customer customer = this.getCustomerById(id);
        this.jdbcTemplate.update(DELETE_CUSTOMER, new Object[]{id});
        return customer;
    }
}

package com.usecase.banking.bankingusecase.customer.api;



import com.usecase.banking.bankingusecase.customer.api.dto.AddCustomerDto;
import com.usecase.banking.bankingusecase.customer.api.dto.UpdateCustomerDto;
import com.usecase.banking.bankingusecase.customer.service.CustomerService;
import com.usecase.banking.bankingusecase.customer.model.Customer;
import com.usecase.banking.bankingusecase.customer.exception.CustomerDoesNotExist;
import com.usecase.banking.bankingusecase.exception.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("get-all")
    ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customerList = this.customerService.getAllCustomer();
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable String  id) throws CustomerDoesNotExist {
        Customer customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PostMapping("addCustomer")
    ResponseEntity<?> createNewCustomer(@Valid @RequestBody AddCustomerDto newCustomer) throws Exception {

        if(newCustomer.getName().isEmpty()) throw new Exception(ErrorMessages.MISSING_CUSTOMER_NAME.getErrorMessage());
        if(newCustomer.getAddress().isEmpty()) throw new Exception(ErrorMessages.MISSING_ADDRESS.getErrorMessage());
        if(newCustomer.getPhoneNumber().isEmpty()) throw new Exception(ErrorMessages.MISSING_PHONE_NUMBER.getErrorMessage());

        Customer customer = new Customer((String)"0",newCustomer.getName(), newCustomer.getPhoneNumber(),newCustomer.getAddress());
        this.customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer,HttpStatus.OK);
    }

    @PatchMapping("updateCustomer")
    ResponseEntity<Customer> updateCustomerById(@Valid @RequestBody UpdateCustomerDto updateCustomer) throws Exception {

        if(updateCustomer.getName().isEmpty()) throw new Exception(ErrorMessages.MISSING_CUSTOMER_NAME.getErrorMessage());
        if(updateCustomer.getAddress().isEmpty()) throw new Exception(ErrorMessages.MISSING_ADDRESS.getErrorMessage());
        if(updateCustomer.getPhoneNumber().isEmpty()) throw new Exception(ErrorMessages.MISSING_PHONE_NUMBER.getErrorMessage());


        Customer customer = new Customer(updateCustomer.getId(),updateCustomer.getName(),updateCustomer.getAddress(),
                updateCustomer.getPhoneNumber());
        this.customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("delete/id/{id}")
    ResponseEntity<String> deleteCustomerById(@PathVariable String id) throws CustomerDoesNotExist {
        String customer = this.customerService.deleteCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

}

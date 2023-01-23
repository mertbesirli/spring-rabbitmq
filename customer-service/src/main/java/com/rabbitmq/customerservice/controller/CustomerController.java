package com.rabbitmq.customerservice.controller;


import com.rabbitmq.customerservice.dto.CreateCustomerRequest;
import com.rabbitmq.customerservice.dto.CustomerDto;
import com.rabbitmq.customerservice.dto.UpdateCustomerRequest;
import com.rabbitmq.customerservice.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController implements CustomerOperations {


    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    @Override
    public CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateCustomer(id, updateCustomerRequest);
    }

    @Override
    public void deleteCustomer(String id) {
        customerService.deleteCustomer(id);
    }


}

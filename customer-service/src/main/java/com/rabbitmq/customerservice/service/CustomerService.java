package com.rabbitmq.customerservice.service;

import com.rabbitmq.customerservice.dto.CreateCustomerRequest;
import com.rabbitmq.customerservice.dto.CustomerDto;
import com.rabbitmq.customerservice.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomer();

    CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest);

    void deleteCustomer(String id);
}

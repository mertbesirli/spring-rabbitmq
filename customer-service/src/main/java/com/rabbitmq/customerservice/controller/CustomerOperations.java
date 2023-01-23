package com.rabbitmq.customerservice.controller;

import com.rabbitmq.customerservice.dto.CreateCustomerRequest;
import com.rabbitmq.customerservice.dto.CustomerDto;
import com.rabbitmq.customerservice.dto.UpdateCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerOperations {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerDto> getAllCustomer();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDto createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDto updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerRequest updateCustomerRequest);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCustomer(@PathVariable String id);

}

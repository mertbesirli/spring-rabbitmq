package com.rabbitmq.customerservice.service;

import com.rabbitmq.customerservice.dto.CreateCustomerRequest;
import com.rabbitmq.customerservice.dto.CustomerDto;
import com.rabbitmq.customerservice.dto.CustomerDtoConverter;
import com.rabbitmq.customerservice.entity.Customer;
import com.rabbitmq.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream().map(customer -> customerDtoConverter.convert(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setId(createCustomerRequest.getId());
        customer.setName(createCustomerRequest.getName());
        customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
        customer.setCity(createCustomerRequest.getCity());

        customerRepository.save(customer);

        return customerDtoConverter.convert(customer);
    }
}

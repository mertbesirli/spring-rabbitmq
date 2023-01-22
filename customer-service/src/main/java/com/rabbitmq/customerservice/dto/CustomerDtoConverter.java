package com.rabbitmq.customerservice.dto;

import com.rabbitmq.customerservice.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setCity(customer.getCity());
        customerDto.setDateOfBirth(customer.getDateOfBirth());

        return customerDto;
    }

}

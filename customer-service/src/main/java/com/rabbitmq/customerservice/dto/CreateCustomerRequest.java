package com.rabbitmq.customerservice.dto;


import com.rabbitmq.customerservice.entity.City;
import lombok.Data;

@Data
public class CreateCustomerRequest {

    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
}

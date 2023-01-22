package com.rabbitmq.customerservice.dto;


import com.rabbitmq.customerservice.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto{
    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
}

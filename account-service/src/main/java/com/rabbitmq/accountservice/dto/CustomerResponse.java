package com.rabbitmq.accountservice.dto;

import com.rabbitmq.accountservice.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
}

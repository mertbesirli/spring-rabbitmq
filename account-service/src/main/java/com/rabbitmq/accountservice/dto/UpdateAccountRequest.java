package com.rabbitmq.accountservice.dto;


import com.rabbitmq.accountservice.entity.City;
import com.rabbitmq.accountservice.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAccountRequest {
    private String customerId;
    private Double balance;
    private Currency currency;
    private City city;
}

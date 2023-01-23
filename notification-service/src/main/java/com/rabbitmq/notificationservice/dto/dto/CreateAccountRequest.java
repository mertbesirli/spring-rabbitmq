package com.rabbitmq.notificationservice.dto.dto;

import com.rabbitmq.notificationservice.dto.dto.City;
import com.rabbitmq.notificationservice.dto.dto.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequest {
    private String id;

    private String customerId;

    private Double balance;

    private Currency currency;

    private City city;
}

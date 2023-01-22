package com.rabbitmq.accountservice.dto;


import com.rabbitmq.accountservice.entity.City;
import com.rabbitmq.accountservice.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto implements Serializable {
    private String id;
    private String costumerId;
    private Double balance;
    private Currency currency;

}

package com.rabbitmq.accountservice.dto;

import com.rabbitmq.accountservice.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .costumerId(account.getCostumerId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .build();
    }

}

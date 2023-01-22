package com.rabbitmq.accountservice.service;

import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    void createAccount(CreateAccountRequest createAccountRequest);
}

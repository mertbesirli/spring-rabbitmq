package com.rabbitmq.accountservice.service;

import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;
import com.rabbitmq.accountservice.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    String createAccount(CreateAccountRequest createAccountRequest);

    void deleteAccount(String id);

    AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest);

    AccountDto addMoney(String id, Double amount);
}


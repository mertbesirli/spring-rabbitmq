package com.rabbitmq.accountservice.controller;

import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;
import com.rabbitmq.accountservice.dto.UpdateAccountRequest;
import com.rabbitmq.accountservice.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController implements AccountOperations{

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<AccountDto> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @Override
    public String createAccount(CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
        return "Account Created Successfully";
    }

    @Override
    public void deleteAccount(String id) {
        accountService.deleteAccount(id);
    }

    @Override
    public AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest) {
        return accountService.updateAccount(id, updateAccountRequest);
    }

}

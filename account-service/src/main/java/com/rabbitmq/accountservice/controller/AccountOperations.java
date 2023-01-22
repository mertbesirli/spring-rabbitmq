package com.rabbitmq.accountservice.controller;

import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface AccountOperations {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AccountDto> getAllAccounts();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String createAccount(@RequestBody CreateAccountRequest createAccountRequest);

}

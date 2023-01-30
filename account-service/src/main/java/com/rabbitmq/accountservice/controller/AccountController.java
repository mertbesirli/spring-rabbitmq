package com.rabbitmq.accountservice.controller;

import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;
import com.rabbitmq.accountservice.dto.UpdateAccountRequest;
import com.rabbitmq.accountservice.service.AccountServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    @CircuitBreaker(name = "customer", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "customer")
    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest) {
        return CompletableFuture.supplyAsync(() -> accountService.createAccount(createAccountRequest));
    }

    @Override
    public CompletableFuture<String> fallbackMethod(CreateAccountRequest createAccountRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, please order after time!");
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

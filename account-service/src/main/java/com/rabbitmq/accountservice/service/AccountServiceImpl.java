package com.rabbitmq.accountservice.service;


import com.rabbitmq.accountservice.dto.*;
import com.rabbitmq.accountservice.entity.Account;
import com.rabbitmq.accountservice.exception.CustomerNotFoundException;
import com.rabbitmq.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;
    private final WebClient webClient;
    private final DirectExchange exchange;
    private final AmqpTemplate amqpTemplate;

    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    @Value("${sample.rabbitmq.queue}")
    String queueName;

    public AccountServiceImpl(AccountRepository accountRepository, AccountDtoConverter accountDtoConverter, WebClient webClient, DirectExchange exchange, AmqpTemplate amqpTemplate) {
        this.accountRepository = accountRepository;
        this.accountDtoConverter = accountDtoConverter;
        this.webClient = webClient;
        this.exchange = exchange;
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();

        return accountList.stream().map(account -> accountDtoConverter.convert(account))
                .collect(Collectors.toList());
    }

    @Override
    public void createAccount(CreateAccountRequest createAccountRequest) {

        String customerIds = createAccountRequest.getCustomerId();

        CustomerResponse[] customerResponses = webClient.get()
                .uri("http://localhost:8081/api/customer",
                        uriBuilder -> uriBuilder.queryParam("id", customerIds).build())
                .retrieve()
                .bodyToMono(CustomerResponse[].class)
                .block();

        assert customerResponses != null;
        List<String> customerList = Arrays.stream(customerResponses)
                .map(customerResponse -> customerResponse.getId())
                .toList();
        if(!customerList.contains(createAccountRequest.getCustomerId())){
            throw new CustomerNotFoundException("Customer Not Found!");
        }

        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .city(createAccountRequest.getCity())
                .costumerId(createAccountRequest.getCustomerId())
                .build();

        accountRepository.save(account);

        amqpTemplate.convertAndSend(exchange.getName(), routingKey, createAccountRequest);

    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest) {
        String customerIds = updateAccountRequest.getCustomerId();

        CustomerResponse[] customerResponses = webClient.get()
                .uri("http://localhost:8081/api/customer",
                        uriBuilder -> uriBuilder.queryParam("id", customerIds).build())
                .retrieve()
                .bodyToMono(CustomerResponse[].class)
                .block();

        assert customerResponses != null;
        List<String> customerList = Arrays.stream(customerResponses)
                .map(customerResponse -> customerResponse.getId())
                .toList();
        if(!customerList.contains(updateAccountRequest.getCustomerId())){
            throw new CustomerNotFoundException("Customer Not Found!");
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setCostumerId(updateAccountRequest.getCustomerId());
            account.setCity(updateAccountRequest.getCity());
            account.setCurrency(updateAccountRequest.getCurrency());
            account.setBalance(updateAccountRequest.getBalance());
        });

        return accountOptional.map(account -> accountDtoConverter.convert(account)).orElse(new AccountDto());
    }

}

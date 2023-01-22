package com.rabbitmq.accountservice.service;


import com.rabbitmq.accountservice.dto.AccountDto;
import com.rabbitmq.accountservice.dto.AccountDtoConverter;
import com.rabbitmq.accountservice.dto.CreateAccountRequest;
import com.rabbitmq.accountservice.dto.CustomerResponse;
import com.rabbitmq.accountservice.entity.Account;
import com.rabbitmq.accountservice.exception.CustomerNotFoundException;
import com.rabbitmq.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;
    private final WebClient webClient;

    public AccountServiceImpl(AccountRepository accountRepository, AccountDtoConverter accountDtoConverter, WebClient webClient) {
        this.accountRepository = accountRepository;
        this.accountDtoConverter = accountDtoConverter;
        this.webClient = webClient;
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
    }
}

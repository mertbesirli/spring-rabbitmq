package com.rabbitmq.accountservice.repository;

import com.rabbitmq.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}

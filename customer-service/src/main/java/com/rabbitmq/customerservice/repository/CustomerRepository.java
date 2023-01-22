package com.rabbitmq.customerservice.repository;

import com.rabbitmq.customerservice.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}

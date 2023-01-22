package com.rabbitmq.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "account")
public class Account implements Serializable {

    @Id
    private String id;
    private String costumerId;
    private Double balance;
    private City city;
    private Currency currency;
}

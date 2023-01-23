package com.rabbitmq.customerservice.entity;


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
@Document(value = "customer")
public class Customer implements Serializable {
    @Id
    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
}

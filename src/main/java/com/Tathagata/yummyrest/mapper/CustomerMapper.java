package com.Tathagata.yummyrest.mapper;

import com.Tathagata.yummyrest.dto.CustomerRequest;
import com.Tathagata.yummyrest.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}

package com.example.mybank.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequestDetails {

    private String requestType;
    private String requestDescription;

    public CustomerRequestDetails() {
        System.out.println("Create ConsumerRequestDetails");
    }
}

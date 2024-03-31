package com.example.mybank.domain;

import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
public class CustomerRegistrationDetails {

    private String accountNumber, address, cardNumber;
}

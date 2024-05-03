package com.example.mybank_xml.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class CustomerRegistrationDetails {

    private String accountNumber, address, cardNumber;
}

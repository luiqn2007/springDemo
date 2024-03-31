package com.example.mybank.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Getter
@Component("transferFundsService")
public class TransferFundsServiceImpl implements TransferFundsService {

    @Value("http://someUrl.com/xyz")
    private String webServiceUrl;
    @Value("true")
    private boolean active;
    @Value("200")
    private long timeout;
    @Value("5")
    private int numberOfRetrialAttempts;

    @Override
    public void transferFunds() {
    }
}

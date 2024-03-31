package com.example.mybank.service;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Singleton
@Named("transferFundsService")
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

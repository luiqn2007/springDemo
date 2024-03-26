package com.example.mybank.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class TransferFundsServiceImpl implements TransferFundsService {

    private final String webServiceUrl;
    private final boolean active;
    private final long timeout;
    private final int numberOfRetrialAttempts;

    @ConstructorProperties({"webServiceUrl", "active", "timeout", "numberOfRetrialAttempts"})
    public TransferFundsServiceImpl(String webServiceUrl, boolean active, long timeout, int numberOfRetrialAttempts) {
        this.webServiceUrl = webServiceUrl;
        this.active = active;
        this.timeout = timeout;
        this.numberOfRetrialAttempts = numberOfRetrialAttempts;
    }

    @Override
    public void transferFunds() {
    }
}

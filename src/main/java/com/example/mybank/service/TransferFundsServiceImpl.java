package com.example.mybank.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
@AllArgsConstructor(onConstructor_ = @ConstructorProperties({"webServiceUrl", "active", "timeout", "numberOfRetrialAttempts"}))
public class TransferFundsServiceImpl implements TransferFundsService {

    private final String webServiceUrl;
    private final boolean active;
    private final long timeout;
    private final int numberOfRetrialAttempts;

    @Override
    public void transferFunds() {
    }
}

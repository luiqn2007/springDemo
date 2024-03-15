package com.example.mybank.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransferFundsServiceImpl implements TransferFundsService {

    private String webServiceUrl;
    private boolean active;
    private long timeout;
    private int numberOfRetrialAttempts;
}

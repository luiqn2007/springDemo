package com.example.mybank_xml.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferFundsServiceImpl implements TransferFundsService {

    private String webServiceUrl;
    private boolean active;
    private long timeout;
    private int numberOfRetrialAttempts;

    @Override
    public void transferFunds() {
    }
}

package com.example.mybank.service;

import com.example.mybank.domain.FixedDepositDetails;

public interface FixedDepositService {

    FixedDepositDetails getFixedDepositDetails(int id);

    int createFixedDeposit(FixedDepositDetails fixedDepositDetails);

    Iterable<FixedDepositDetails> getHighValueFds(int minValue);
}

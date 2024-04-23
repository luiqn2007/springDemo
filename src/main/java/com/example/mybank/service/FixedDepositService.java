package com.example.mybank.service;

import com.example.mybank.domain.FixedDepositDetails;

import java.util.List;

public interface FixedDepositService {

    FixedDepositDetails getFixedDepositDetails(int id);

    int createFixedDeposit(FixedDepositDetails fixedDepositDetails);

    void deleteFixedDeposit(int id);

    Iterable<FixedDepositDetails> getHighValueFds(int minValue);

    Iterable<FixedDepositDetails> getAllFds(int amount, int tenure);

    List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId);

    List<FixedDepositDetails> getFixedDeposits();
}

package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.respository.FixedDepositRepository;

import java.util.List;

public interface FixedDepositService {

    FixedDepositDetails getFixedDepositDetails(int id);

    void createFixedDeposit(FixedDepositDetails fixedDepositDetails);

    void deleteFixedDeposit(int id);

    Iterable<FixedDepositDetails> getHighValueFds(int minValue);

    Iterable<FixedDepositDetails> getAllFds(int amount, int tenure);

    List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId);

    List<FixedDepositDetails> getFixedDeposits();

    void setFixedDepositRepository(FixedDepositRepository fixedDepositRepository);
}

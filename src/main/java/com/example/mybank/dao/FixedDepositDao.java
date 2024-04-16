package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface FixedDepositDao {

    Logger LOGGER = LogManager.getLogger();

    FixedDepositDetails getFixedDeposit(int id);

    int createFixedDetail(FixedDepositDetails fixedDepositDetails);

    Iterable<FixedDepositDetails> getHighValueFds(int minValue);

    Iterable<FixedDepositDetails> getAllFds(int amount, int tenure);

    List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId);

    List<FixedDepositDetails> getFixedDeposits();
}

package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface FixedDepositDao {

    Logger LOGGER = LogManager.getLogger();

    FixedDepositDetails getFixedDeposit(int id);

    int createFixedDetail(FixedDepositDetails fixedDepositDetails);
}

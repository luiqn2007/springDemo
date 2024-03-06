package com.example.mybank.chapter02.service;

import com.example.mybank.chapter02.FixedDepositDetails;
import com.example.mybank.chapter02.dao.FixedDepositDao;

public interface FixedDepositService {
    void setFixedDepositDao(FixedDepositDao fixedDepositDao);

    FixedDepositDao getFixedDepositDao();

    FixedDepositDetails getFixedDepositDetails(long id);

    boolean createFixedDeposit(FixedDepositDetails fdd);
}

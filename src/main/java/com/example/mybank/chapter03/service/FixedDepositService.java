package com.example.mybank.chapter03.service;

import com.example.mybank.chapter03.beans.FixedDepositDetails;
import com.example.mybank.chapter03.dao.FixedDepositDao;

public interface FixedDepositService {
    void setFixedDepositDao(FixedDepositDao fixedDepositDao);

    FixedDepositDetails getFixedDepositDetails(long id);

    boolean createFixedDeposit(FixedDepositDetails fdd);
}

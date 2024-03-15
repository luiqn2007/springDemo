package com.example.mybank.service;

import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.beans.FixedDepositDetails;

public interface FixedDepositService {

    void setFixedDepositDao(FixedDepositDao fixedDepositDao);

    FixedDepositDao getFixedDepositDao();

    FixedDepositDetails getFixedDepositDetails(long id);

    boolean createFixedDeposit(FixedDepositDetails fdd);
}

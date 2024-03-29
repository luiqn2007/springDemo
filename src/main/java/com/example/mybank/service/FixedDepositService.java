package com.example.mybank.service;

import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.event.EventSender;

public interface FixedDepositService {

    void setFixedDepositDao(FixedDepositDao fixedDepositDao);

    FixedDepositDao getFixedDepositDao();

    EventSender getEventSender();

    FixedDepositDetails getFixedDepositDetails(long id);

    boolean createFixedDeposit(FixedDepositDetails fdd);

    void createFixedDeposit(long id, float depositAmount, int tenure, String email) throws Exception;
}

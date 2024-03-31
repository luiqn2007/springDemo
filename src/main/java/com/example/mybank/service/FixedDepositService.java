package com.example.mybank.service;

import com.example.mybank.dao.FixedDepositDao;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.event.EventSender;

public interface FixedDepositService {

    FixedDepositDao getFixedDepositDao();

    EventSender getEventSender();

    FixedDepositDetails getFixedDepositDetails(long id);

    boolean createFixedDeposit(FixedDepositDetails fdd);
}

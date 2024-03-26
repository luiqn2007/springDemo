package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;

public interface FixedDepositController {

    FixedDepositService getFixedDepositService();

    void setFixedDepositService(FixedDepositService fixedDepositService);

    FixedDepositDetails get();

    boolean submit(FixedDepositDetails fixedDepositDetails);
}

package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.FixedDepositDetails;
import com.example.mybank.chapter03.service.FixedDepositService;

public interface FixedDepositController {
    void setFixedDepositService(FixedDepositService fixedDepositService);

    FixedDepositService getFixedDepositService();

    boolean submit();

    FixedDepositDetails get();
}

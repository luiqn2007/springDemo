package com.example.mybank.chapter02.controller;

import com.example.mybank.chapter02.FixedDepositDetails;
import com.example.mybank.chapter02.service.FixedDepositService;

public interface FixedDepositControllerV2 {
    void setFixedDepositService(FixedDepositService fixedDepositService);

    FixedDepositService getFixedDepositService();

    boolean submit();

    FixedDepositDetails get();
}

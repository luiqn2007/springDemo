package com.example.mybank.controller;

import com.example.mybank.beans.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;

public interface FixedDepositController {
    boolean submit();

    FixedDepositService getFixedDepositService();

    void setFixedDepositService(FixedDepositService fixedDepositService);

    FixedDepositDetails get();
}

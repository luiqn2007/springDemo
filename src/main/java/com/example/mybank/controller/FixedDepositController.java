package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;

public interface FixedDepositController {

    void setFixedDepositService(FixedDepositService fixedDepositService);

    FixedDepositDetails get();

    int submit(FixedDepositDetails fixedDepositDetails);
}

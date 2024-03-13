package com.example.mybank.controller;

import com.example.mybank.beans.FixedDepositDetails;

public interface FixedDepositController {
    boolean submit();

    FixedDepositDetails get();
}

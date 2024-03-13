package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.beans.FixedDepositDetails;

public interface FixedDepositController {
    boolean submit();

    FixedDepositDetails get();
}

package com.example.mybank.service;

import com.example.mybank.domain.BankAccountDetails;

public interface FundTransferService {

    void transferFunds(BankAccountDetails sender, BankAccountDetails receiver);
}

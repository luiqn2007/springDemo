package com.example.mybank.service;

import com.example.mybank.domain.Account;

public interface FundTransferService {

    void transferFunds(Account sender, Account receiver);
}

package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;

public interface FundTransferService {

    void transferFunds(BankAccountDetails sender, BankAccountDetails receiver);
}

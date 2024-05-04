package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NormalDiffBank implements FundTransferService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void transferFunds(BankAccountDetails sender, BankAccountDetails receiver) {
        LOGGER.info("NormalDiffBank --> Transfering funds");
    }
}

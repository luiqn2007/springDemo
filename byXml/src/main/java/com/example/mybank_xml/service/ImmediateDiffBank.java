package com.example.mybank_xml.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import com.example.mybank_xml.domain.BankAccountDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
@Named
@FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.DIFF)
public class ImmediateDiffBank implements FundTransferService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void transferFunds(BankAccountDetails sender, BankAccountDetails receiver) {
        LOGGER.info("ImmediateDiffBank --> Transfering funds");
    }
}

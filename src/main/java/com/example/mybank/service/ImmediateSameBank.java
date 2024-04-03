package com.example.mybank.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import com.example.mybank.domain.BankAccountDetails;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
@Named
@FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.SAME)
public class ImmediateSameBank implements FundTransferService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void transferFunds(BankAccountDetails sender, BankAccountDetails receiver) {
        LOGGER.info("ImmediateSameBank --> Transfering funds");
    }
}

package com.example.mybank.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import com.example.mybank.domain.Account;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
@Named
@FundTransfer(transformSpeed = TransformMode.NORMAL, bankType = BankType.DIFF)
public class NormalDiffBank implements FundTransferService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void transferFunds(Account sender, Account receiver) {
        LOGGER.info("NormalDiffBank --> Transfering funds");
    }
}

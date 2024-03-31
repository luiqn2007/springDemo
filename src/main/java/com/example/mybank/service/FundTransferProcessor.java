package com.example.mybank.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;

@Getter
@Singleton
@Named
public class FundTransferProcessor {

    @Inject
    @FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.SAME)
    private FundTransferService sameBankImmediateFundTransferService;

    @Inject
    @FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.DIFF)
    private FundTransferService diffBankImmediateFundTransferService;

    @Inject
    @FundTransfer(transformSpeed = TransformMode.NORMAL, bankType = BankType.SAME)
    private FundTransferService sameBankNormalFundTransferService;

    @Inject
    @FundTransfer(transformSpeed = TransformMode.NORMAL, bankType = BankType.DIFF)
    private FundTransferService diffBankNormalFundTransferService;
}

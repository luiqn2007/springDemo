package com.example.mybank.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FundTransferProcessor {

    @Autowired
    @FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.SAME)
    private FundTransferService sameBankImmediateFundTransferService;

    @Autowired
    @FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.DIFF)
    private FundTransferService diffBankImmediateFundTransferService;

    @Autowired
    @FundTransfer(transformSpeed = TransformMode.NORMAL, bankType = BankType.SAME)
    private FundTransferService sameBankNormalFundTransferService;

    @Autowired
    @FundTransfer(transformSpeed = TransformMode.NORMAL, bankType = BankType.DIFF)
    private FundTransferService diffBankNormalFundTransferService;
}

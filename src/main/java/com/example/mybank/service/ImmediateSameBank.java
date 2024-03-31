package com.example.mybank.service;

import com.example.mybank.annotation.BankType;
import com.example.mybank.annotation.FundTransfer;
import com.example.mybank.annotation.TransformMode;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named
@FundTransfer(transformSpeed = TransformMode.IMMEDIATE, bankType = BankType.SAME)
public class ImmediateSameBank implements FundTransferService {
}

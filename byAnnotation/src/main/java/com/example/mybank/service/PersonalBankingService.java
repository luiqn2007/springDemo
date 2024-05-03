package com.example.mybank.service;

import com.example.mybank.domain.BankStatement;

/**
 * 允许客户端检索银行账户对账单
 */
public interface PersonalBankingService {

    BankStatement getMiniStatement();
}

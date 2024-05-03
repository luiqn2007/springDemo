package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankStatement;

/**
 * 允许客户端检索银行账户对账单
 */
public interface PersonalBankingService {

    BankStatement getMiniStatement();
}

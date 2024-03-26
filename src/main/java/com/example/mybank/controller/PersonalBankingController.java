package com.example.mybank.controller;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.service.PersonalBankingService;

public interface PersonalBankingController {

    BankStatement getMiniStatement();

    void setPersonalBankingService(PersonalBankingService personalBankingService);
}

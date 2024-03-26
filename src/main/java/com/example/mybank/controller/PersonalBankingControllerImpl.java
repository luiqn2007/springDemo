package com.example.mybank.controller;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.service.PersonalBankingService;
import lombok.Getter;
import lombok.Setter;

@Setter
public class PersonalBankingControllerImpl implements PersonalBankingController {

    private PersonalBankingService personalBankingService;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingService.getMiniStatement();
    }
}

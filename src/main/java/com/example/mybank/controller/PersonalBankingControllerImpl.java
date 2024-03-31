package com.example.mybank.controller;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.service.PersonalBankingService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;

@Setter
@Singleton
@Named("personalBankingController")
public class PersonalBankingControllerImpl implements PersonalBankingController {

    @Inject
    private PersonalBankingService personalBankingService;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingService.getMiniStatement();
    }
}

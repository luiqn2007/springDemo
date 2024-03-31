package com.example.mybank.controller;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.service.PersonalBankingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Setter
@Component("personalBankingController")
public class PersonalBankingControllerImpl implements PersonalBankingController {

    @Autowired
    @Qualifier("personalBankingService")
    private PersonalBankingService personalBankingService;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingService.getMiniStatement();
    }
}

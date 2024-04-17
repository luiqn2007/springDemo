package com.example.mybank.controller;

import com.example.mybank.domain.BankStatement;
import com.example.mybank.service.PersonalBankingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Setter
@Controller("personalBankingController")
public class PersonalBankingControllerImpl implements PersonalBankingController {

    @Autowired
    private PersonalBankingService personalBankingService;

    @Override
    public BankStatement getMiniStatement() {
        return personalBankingService.getMiniStatement();
    }
}

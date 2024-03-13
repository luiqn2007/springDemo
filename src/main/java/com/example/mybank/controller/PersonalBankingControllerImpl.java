package com.example.mybank.controller;

import com.example.mybank.service.PersonalBankingService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalBankingControllerImpl implements PersonalBankingController {

    private PersonalBankingService personalBankingService;

}

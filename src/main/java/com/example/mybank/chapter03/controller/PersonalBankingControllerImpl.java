package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.service.PersonalBankingService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalBankingControllerImpl implements PersonalBankingController {

    private PersonalBankingService personalBankingService;

}

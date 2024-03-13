package com.example.mybank.controller;

import com.example.mybank.service.PersonalBankingService;

public interface PersonalBankingController {

    PersonalBankingService getPersonalBankingService();

    void setPersonalBankingService(PersonalBankingService personalBankingService);
}

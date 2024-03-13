package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.service.PersonalBankingService;

public interface PersonalBankingController {

    PersonalBankingService getPersonalBankingService();

    void setPersonalBankingService(PersonalBankingService personalBankingService);
}

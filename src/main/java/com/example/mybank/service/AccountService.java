package com.example.mybank.service;

import com.example.mybank.domain.Account;

public interface AccountService {

    int createAccount(Account account);

    Account getAccount(int id);
}

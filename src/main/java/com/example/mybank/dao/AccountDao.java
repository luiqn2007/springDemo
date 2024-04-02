package com.example.mybank.dao;

import com.example.mybank.domain.Account;

public interface AccountDao {

    int createAccount(Account account);

    Account getAccount(int id);
}

package com.example.mybank.service;

import com.example.mybank.dao.AccountDao;
import com.example.mybank.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public int createAccount(Account account) {
        return accountDao.createAccount(account);
    }

    @Override
    public Account getAccount(int id) {
        return accountDao.getAccount(id);
    }
}

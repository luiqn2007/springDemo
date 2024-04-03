package com.example.mybank.service;

import com.example.mybank.dao.BankAccountDao;
import com.example.mybank.domain.BankAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Override
    public int createAccount(BankAccountDetails bankAccountDetails) {
        return bankAccountDao.createBankAccount(bankAccountDetails);
    }

    @Override
    public BankAccountDetails getBankAccount(int id) {
        return bankAccountDao.getBankAccount(id);
    }

    @Override
    public void subtractAmount(int bankAccountId, float amount) {
        bankAccountDao.subtractAmount(bankAccountId, amount);
    }
}

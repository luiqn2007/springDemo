package com.example.mybank.service;

import com.example.mybank.dao.BankAccountDao;
import com.example.mybank.domain.BankAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Profile({"hibernate", "jdbc"})
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Override
    @Transactional
    public void createAccount(BankAccountDetails bankAccountDetails) {
        bankAccountDao.createBankAccount(bankAccountDetails);
    }

    @Override
    @Transactional
    public BankAccountDetails getBankAccount(int id) {
        return bankAccountDao.getBankAccount(id);
    }

    @Override
    @Transactional
    public void subtractAmount(int bankAccountId, float amount) {
        bankAccountDao.subtractAmount(bankAccountId, amount);
    }
}

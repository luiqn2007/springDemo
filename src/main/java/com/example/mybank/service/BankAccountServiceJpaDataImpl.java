package com.example.mybank.service;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.repository.BankAccountRepository;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Profile("jpa")
public class BankAccountServiceJpaDataImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    @Transactional
    public void createAccount(BankAccountDetails bankAccountDetails) {
        boolean isDuplicate =
                ((BankAccountService) AopContext.currentProxy()).isDuplicateAccount(bankAccountDetails.getAccountId());
        if (!isDuplicate) {
            bankAccountRepository.save(bankAccountDetails);
        }
    }

    @Override
    @Transactional
    public BankAccountDetails getBankAccount(int id) {
        return bankAccountRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void subtractAmount(int bankAccountId, float amount) {
        bankAccountRepository.subtractFromAccount(bankAccountId, amount);
    }

    @Override
    public boolean isDuplicateAccount(int accountId) {
        return bankAccountRepository.findById(accountId).isPresent();
    }
}

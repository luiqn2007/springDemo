package com.example.mybank.service;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.QBankAccountDetails;
import com.example.mybank.repository.BankAccountRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void createAccount(BankAccountDetails bankAccountDetails) {
        bankAccountRepository.save(bankAccountDetails);
    }

    @Override
    public BankAccountDetails getBankAccount(int id) {
        Predicate predicate = QBankAccountDetails.bankAccountDetails.accountId.eq(id);
        return bankAccountRepository.findOne(predicate).orElseThrow();
    }

    @Override
    public void subtractAmount(int bankAccountId, float amount) {
        Predicate predicate = QBankAccountDetails.bankAccountDetails.accountId.eq(bankAccountId);
        BankAccountDetails accountDetails = bankAccountRepository.findOne(predicate).orElseThrow();
        accountDetails.setBalanceAmount(accountDetails.getBalanceAmount() - amount);
        bankAccountRepository.save(accountDetails);
    }

    @Override
    public boolean isDuplicateAccount(int accountId) {
        return bankAccountRepository.findById(accountId).isPresent();
    }
}

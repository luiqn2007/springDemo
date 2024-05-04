package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;
import com.example.mybank_xml.domain.QBankAccountDetails;
import com.example.mybank_xml.respository.BankAccountRepository;
import com.querydsl.core.types.Predicate;
import lombok.Setter;

@Setter
public class BankAccountServiceImpl implements BankAccountService {

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

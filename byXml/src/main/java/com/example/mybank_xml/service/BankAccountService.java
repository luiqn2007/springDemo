package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;
import com.example.mybank_xml.respository.BankAccountRepository;

public interface BankAccountService {

    void createAccount(BankAccountDetails bankAccountDetails);

    BankAccountDetails getBankAccount(int id);

    void subtractAmount(int bankAccountId, float amount);

    boolean isDuplicateAccount(int accountId);

    void setBankAccountRepository(BankAccountRepository bankAccountRepository);
}

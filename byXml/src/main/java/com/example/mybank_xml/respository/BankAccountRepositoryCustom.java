package com.example.mybank_xml.respository;

public interface BankAccountRepositoryCustom {

    void subtractFromAccount(int bankAccountId, float amount);

    boolean isDuplicateAccount(int bankAccountId);
}

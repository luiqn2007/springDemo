package com.example.mybank.service;

import com.example.mybank.domain.QFixedDepositDetails;
import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import com.example.mybank.mongodb_domain.QMongoBankAccountDetails;
import com.example.mybank.mongodb_repository.BankAccountRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("accountService")
@Profile("mongodb")
public class BankAccountServiceMongoDBImpl {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void createAccount(MongoBankAccountDetails bankAccountDetails) {
        bankAccountRepository.save(bankAccountDetails);
    }

    public MongoBankAccountDetails getBankAccount(String id) {
        return bankAccountRepository.findById(id).orElseThrow();
    }

    public void subtractAmount(String bankAccountId, float amount) {
        bankAccountRepository.subtractFromAccount(bankAccountId, amount);
    }

    public Iterable<MongoBankAccountDetails> getHighValueFds(float minValue) {
        BooleanExpression expression = QMongoBankAccountDetails.mongoBankAccountDetails.fixedDeposits.any().active.eq("Y")
                .and(QFixedDepositDetails.fixedDepositDetails.depositAmount.goe(minValue))
                .and(QFixedDepositDetails.fixedDepositDetails.tenure.between(6, 12));
        return bankAccountRepository.findAll(expression);
    }
}

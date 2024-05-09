package com.example.webflux.repository;

import com.example.webflux.domain.BankAccountDetails;
import com.example.webflux.domain.FixedDepositDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class BankAccountRepositoryImpl implements BankAccountRepositoryCustom {

    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<Void> subtractFromAccount(String bankAccountId, int amount) {
        return template.findById(bankAccountId, BankAccountDetails.class)
                .handle((bankAccount, sink) -> {
                    if (bankAccount.getBalance() < amount) {
                        sink.error(new IllegalArgumentException("Not enough money on account"));
                        return;
                    }

                    bankAccount.setBalance(bankAccount.getBalance() - amount);
                    sink.next(template.save(bankAccount).subscribe());
                })
                .then();
    }

    @Override
    public Mono<Boolean> isDuplicateAccount(String bankAccountId) {
        return template.findById(bankAccountId, BankAccountDetails.class).map(Objects::nonNull);
    }

    @Override
    public Mono<Void> addFixedDeposit(String bankAccountId, int amount) {
        return template.findById(bankAccountId, BankAccountDetails.class)
                .map(bankAccount -> addFd(bankAccount, amount).subscribe())
                .then();
    }

    private Mono<BankAccountDetails> addFd(BankAccountDetails bankAccount, int amount) {
        if (bankAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Not enough money on account");
        }

        FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
        fixedDepositDetails.setFdAmount(amount);

        bankAccount.getFixedDeposits().add(fixedDepositDetails);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        return template.save(bankAccount);
    }
}

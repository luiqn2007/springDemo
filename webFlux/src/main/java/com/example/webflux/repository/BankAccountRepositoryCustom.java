package com.example.webflux.repository;

import reactor.core.publisher.Mono;

public interface BankAccountRepositoryCustom {

    Mono<Void> subtractFromAccount(String bankAccountId, int amount);

    Mono<Boolean> isDuplicateAccount(String bankAccountId);

    Mono<Void> addFixedDeposit(String bankAccountId, int amount);
}

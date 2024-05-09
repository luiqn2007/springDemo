package com.example.webflux.repository;

import com.example.webflux.domain.BankAccountDetails;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountRepository extends ReactiveMongoRepository<BankAccountDetails, String> {

    Mono<Long> countByBalance(float balance);

    Flux<BankAccountDetails> removeByBalance(float balanceAmount);

    @Query("{'balance' : {'$lte':  ?0}}")
    Flux<BankAccountDetails> findByCustomQuery(int balanceAmount);
}

package com.example.mybank.mongodb_repository;

import com.example.mybank.mongodb_domain.MongoBankAccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BankAccountRepository extends MongoRepository<MongoBankAccountDetails, String>,
        QuerydslPredicateExecutor<MongoBankAccountDetails>,
        BankAccountRepositoryCustom {

    List<MongoBankAccountDetails> findByFixedDepositsTenureAndFixedDepositsFdAmount(int tenure, int fdAmount);

    @Async
    CompletableFuture<List<MongoBankAccountDetails>> findAllByBalanceGreaterThan(int balance);

    @Query("{'balance': {$lt: ?0}}")
    List<MongoBankAccountDetails> findByCustomQuery(int balance);
}

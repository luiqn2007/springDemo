package com.example.webflux.repository;

import com.example.webflux.domain.BankAccountDetails;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankAccountRepository extends ReactiveMongoRepository<BankAccountDetails, String> {

    Single<Long> countByBalanceAmount(float balance);

    Flowable<BankAccountDetails> removeByBalanceAmount(float balanceAmount);


}

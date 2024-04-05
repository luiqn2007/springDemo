package com.example.mybank.repository;

import com.example.mybank.domain.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BankAccountQuerydslRepository
        extends JpaRepository<BankAccountDetails, Integer>, QuerydslPredicateExecutor<BankAccountDetails> {
}

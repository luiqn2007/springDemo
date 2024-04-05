package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FixedDepositQuerydslRepository
        extends JpaRepository<FixedDepositDetails, Integer>, QuerydslPredicateExecutor<FixedDepositDetails> {
}

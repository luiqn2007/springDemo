package com.example.mybank.repository;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FixedDepositQuerydslRepository
        extends JpaRepository<FixedDepositDetails, Integer>, QuerydslPredicateExecutor<FixedDepositDetails> {

    List<FixedDepositDetails> findFixedDepositDetailsByBankAccountId(BankAccountDetails bankAccountId);
}

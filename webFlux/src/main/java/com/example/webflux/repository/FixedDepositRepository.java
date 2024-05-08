package com.example.webflux.repository;

import com.example.webflux.domain.BankAccountDetails;
import com.example.webflux.domain.FixedDepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FixedDepositRepository extends
        JpaRepository<FixedDepositDetails, Integer>,
        QuerydslPredicateExecutor<FixedDepositDetails>,
        FixedDepositRepositoryCustom {

    List<FixedDepositDetails> findAllByBankAccountId(BankAccountDetails bankAccountId);

    List<FixedDepositDetails> findAllByDepositAmountGreaterThanEqual(int depositAmount);

    List<FixedDepositDetails> findAllByDepositAmountAndTenure(int depositAmount, int tenure);
}

package com.example.mybank_xml.respository;

import com.example.mybank_xml.domain.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BankAccountRepository extends
        JpaRepository<BankAccountDetails, Integer>,
        QuerydslPredicateExecutor<BankAccountDetails>,
        BankAccountRepositoryCustom {
}

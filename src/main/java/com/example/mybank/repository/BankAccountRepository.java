package com.example.mybank.repository;

import com.example.mybank.domain.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountDetails, Integer>, BankAccountRepositoryCustom {
}

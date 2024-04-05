package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedDepositRepository extends JpaRepository<FixedDepositDetails, Integer> {
}

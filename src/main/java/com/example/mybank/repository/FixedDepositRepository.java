package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedDepositRepository extends JpaRepository<FixedDepositDetails, Integer>, FixedDepositRepositoryCustom {

    List<FixedDepositDetails> findAllByDepositAmountGreaterThanEqual(int minValue);

    List<FixedDepositDetails> findAllByDepositAmountAndTenure(int depositAmount, int tenure);
}

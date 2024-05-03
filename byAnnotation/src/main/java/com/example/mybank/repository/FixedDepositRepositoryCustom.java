package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;

import java.util.List;

public interface FixedDepositRepositoryCustom {

    List<FixedDepositDetails> getInactiveFds();

    void addInactiveFixedDeposit(FixedDepositDetails fd);

    void setFixedDepositAsActive(FixedDepositDetails fd);
}

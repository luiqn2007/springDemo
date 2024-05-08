package com.example.webflux.repository;

import com.example.webflux.domain.FixedDepositDetails;

import java.util.List;

public interface FixedDepositRepositoryCustom {

    List<FixedDepositDetails> getInactiveFds();

    void addInactiveFixedDeposit(FixedDepositDetails fd);

    void setFixedDepositAsActive(FixedDepositDetails fd);

    void closeFixedDeposit(int id);
}

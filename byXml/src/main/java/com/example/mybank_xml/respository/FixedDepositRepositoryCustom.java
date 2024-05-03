package com.example.mybank_xml.respository;

import com.example.mybank_xml.domain.FixedDepositDetails;

import java.util.List;

public interface FixedDepositRepositoryCustom {

    List<FixedDepositDetails> getInactiveFds();

    void addInactiveFixedDeposit(FixedDepositDetails fd);

    void setFixedDepositAsActive(FixedDepositDetails fd);
}

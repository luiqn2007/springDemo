package com.example.mybank.chapter02.dao;

import com.example.mybank.chapter02.FixedDepositDetails;

import java.util.HashMap;
import java.util.Map;

public class FixedDepositDefaultDao extends FixedDepositDao {

    private Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();

    @Override
    public FixedDepositDetails getFixedDeposit(long id) {
        return fixedDeposits.get(id);
    }

    @Override
    public boolean createFixedDetail(FixedDepositDetails fdd) {
        fixedDeposits.put(fdd.id(), fdd);
        return true;
    }


}

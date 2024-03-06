package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.FixedDepositDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 */
public class FixedDepositIbatisDao extends FixedDepositDao {

    private final Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();

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

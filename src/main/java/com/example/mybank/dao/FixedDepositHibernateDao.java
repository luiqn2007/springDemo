package com.example.mybank.dao;

import com.example.mybank.beans.FixedDepositDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 */
public class FixedDepositHibernateDao extends FixedDepositDao {

    private final Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();

    @Override
    public FixedDepositDetails getFixedDeposit(long id) {
        return fixedDeposits.get(id);
    }

    @Override
    public boolean createFixedDetail(FixedDepositDetails fdd) {
        fixedDeposits.put(fdd.getId(), fdd);
        return true;
    }
}

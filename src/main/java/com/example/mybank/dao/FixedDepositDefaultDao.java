package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;

public class FixedDepositDefaultDao extends FixedDepositDao {

    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();

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

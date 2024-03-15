package com.example.mybank.dao;

import com.example.mybank.DatabaseInfo;
import com.example.mybank.beans.FixedDepositDetails;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class FixedDepositJdbcDao extends FixedDepositDao {

    private final Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();

    @Setter
    private DatabaseInfo databaseInfo;

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

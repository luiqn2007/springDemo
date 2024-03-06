package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.DatabaseInfo;
import com.example.mybank.chapter03.FixedDepositDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 */
public class FixedDepositJdbcDao extends FixedDepositDao {

    private final Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();
    private DatabaseInfo databaseInfo;

    public void setDatabaseInfo(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

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

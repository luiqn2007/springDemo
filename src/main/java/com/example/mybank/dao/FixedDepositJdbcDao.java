package com.example.mybank.dao;

import com.example.mybank.DatabaseConnection;
import com.example.mybank.beans.DatabaseInfo;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import lombok.Setter;

public class FixedDepositJdbcDao extends FixedDepositDao {

    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();
    private DatabaseConnection connection;

    @Setter
    private DatabaseInfo databaseInfo;

    public void initializeDbConnection() {
        System.out.println("FixedDepositJdbcDao: Initializing database connection");
        connection = DatabaseConnection.getInstance();
    }

    public void releaseDbConnection() {
        System.out.println("FixedDepositJdbcDao: Release database connection");
        connection.releaseConnection();
    }

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

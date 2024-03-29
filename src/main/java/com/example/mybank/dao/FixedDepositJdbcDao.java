package com.example.mybank.dao;

import com.example.mybank.beans.DatabaseInfo;
import com.example.mybank.common.InstanceValidator;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import lombok.Setter;

public class FixedDepositJdbcDao extends FixedDepositDao implements InstanceValidator {

    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();
    private DatabaseConnection connection;
    @Setter
    private FixedDepositDetails fixedDepositDetails;

    public void initializeDbConnection() {
        LOGGER.info("FixedDepositJdbcDao: Initializing database connection");
        connection = DatabaseConnection.getInstance();
    }

    public void releaseDbConnection() {
        LOGGER.info("FixedDepositJdbcDao: Release database connection");
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

    @Override
    public boolean createFixedDetail(long id, float depositAmount, int tenure, String email) {
        fixedDepositDetails.setId(id);
        fixedDepositDetails.setDepositAmount(depositAmount);
        fixedDepositDetails.setTenure(tenure);
        fixedDepositDetails.setEmail(email);
        LOGGER.info("FixedDepositJdbcDao: Creating fixed deposit details");
        return true;
    }

    @Override
    public void validateInstance() {
        LOGGER.info("FixedDepositJdbcDao: Validating instance");
        if (connection == null) {
            LOGGER.error("FixedDepositJdbcDao: Failed to obtain DatabaseConnection instance.");
        }
    }
}

package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.FixedDepositDetails;
import com.example.mybank.chapter03.utils.DatabaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class FixedDepositDao {

    protected static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;
    private DatabaseOperations databaseOperations;

    public FixedDepositDao() {
        LOGGER.info("initializing");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriveClass(String driveClass) {
        this.driveClass = driveClass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatabaseOperations getDatabaseOperations() {
        return databaseOperations;
    }

    public void setDatabaseOperations(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    public abstract FixedDepositDetails getFixedDeposit(long id);

    public abstract boolean createFixedDetail(FixedDepositDetails fdd);
}

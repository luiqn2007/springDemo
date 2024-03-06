package com.example.mybank.chapter02.dao;

import com.example.mybank.chapter02.FixedDepositDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class FixedDepositDao {

    protected static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;

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

    public abstract FixedDepositDetails getFixedDeposit(long id);

    public abstract boolean createFixedDetail(FixedDepositDetails fdd);
}

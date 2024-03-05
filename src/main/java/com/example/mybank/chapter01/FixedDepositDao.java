package com.example.mybank.chapter01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FixedDepositDao {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;

    private Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<>();

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

    public FixedDepositDetails getFixedDeposit(long id) {
        return fixedDeposits.get(id);
    }

    public boolean createFixedDetail(FixedDepositDetails fdd) {
        fixedDeposits.put(fdd.id(), fdd);
        return true;
    }
}

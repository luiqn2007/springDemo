package com.example.mybank.chapter03.dao;

import com.example.mybank.chapter03.beans.FixedDepositDetails;
import com.example.mybank.chapter03.utils.DatabaseOperations;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Setter
public abstract class FixedDepositDao {

    protected static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;

    @Getter
    private DatabaseOperations databaseOperations;

    public FixedDepositDao() {
        LOGGER.info("initializing");
    }

    public abstract FixedDepositDetails getFixedDeposit(long id);

    public abstract boolean createFixedDetail(FixedDepositDetails fdd);
}

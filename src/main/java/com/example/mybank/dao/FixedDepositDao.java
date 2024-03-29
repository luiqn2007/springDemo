package com.example.mybank.dao;

import com.example.mybank.beans.DatabaseInfo;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.utils.DatabaseOperations;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Setter
@Getter
public abstract class FixedDepositDao implements Dao {

    protected static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;
    private DatabaseOperations databaseOperations;
    private DatabaseInfo databaseInfo;

    public FixedDepositDao() {
        LOGGER.info("initializing");
    }

    public abstract FixedDepositDetails getFixedDeposit(long id);

    public abstract boolean createFixedDetail(FixedDepositDetails fdd);

    public abstract boolean createFixedDetail(long id, float depositAmount, int tenure, String email);

    public abstract void initializeDbConnection() throws Exception;

    public abstract void releaseDbConnection() throws Exception;
}

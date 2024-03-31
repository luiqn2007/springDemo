package com.example.mybank.dao;

import com.example.mybank.beans.DatabaseInfo;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.utils.DatabaseOperations;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
public abstract class FixedDepositDao {

    protected static final Logger LOGGER = LogManager.getLogger();

    private String url, driveClass, username, password;
    @Autowired
    private DatabaseOperations databaseOperations;
    @Autowired
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

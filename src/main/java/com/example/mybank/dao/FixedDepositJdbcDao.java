package com.example.mybank.dao;

import com.example.mybank.common.InstanceValidator;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.utils.DatabaseConnection;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Primary
@Singleton
@Named("fixedDepositDao")
@Profile("jdbc")
public class FixedDepositJdbcDao extends FixedDepositDao implements InstanceValidator {

    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();
    private DatabaseConnection connection;
    @Setter
    private FixedDepositDetails fixedDepositDetails;

    @PostConstruct
    public void initializeDbConnection() {
        LOGGER.info("FixedDepositJdbcDao: Initializing database connection");
        connection = DatabaseConnection.getInstance();
    }

    @PreDestroy
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
    public void validateInstance() {
        LOGGER.info("FixedDepositJdbcDao: Validating instance");
        if (connection == null) {
            LOGGER.error("FixedDepositJdbcDao: Failed to obtain DatabaseConnection instance.");
        }
    }
}

package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;

@Singleton
@Named("fixedDepositDao")
@Profile("ibatis")
public class FixedDepositIbatisDao extends FixedDepositDao implements InitializingBean, DisposableBean {

    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();

    @Override
    public void destroy() throws Exception {
        LOGGER.info("FixedDepositIbatisDao: Destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("FixedDepositIbatisDao: Initializing");
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
    public void initializeDbConnection() throws Exception {
    }

    @Override
    public void releaseDbConnection() throws Exception {
    }
}

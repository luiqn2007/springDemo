package com.example.mybank.dao;

import com.example.mybank.common.DependencyResolver;
import com.example.mybank.common.MyApplicationContext;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.context.annotation.Profile;

/**
 * todo
 */
@Singleton
@Named("fixedDepositDao")
@Profile("hibernate")
public class FixedDepositHibernateDao extends FixedDepositDao implements DependencyResolver {

    private FixedDepositDao fixedDepositDao;
    private final Long2ObjectMap<FixedDepositDetails> fixedDeposits = new Long2ObjectArrayMap<>();

    @Override
    public void resolveDependency(MyApplicationContext context) {
        LOGGER.info("FixedDepositHibernateDao: Resolving dependency for FixedDepositHibernateDao");
        fixedDepositDao = context.getBean(FixedDepositDao.class);
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

package com.example.mybank.dao;

import com.example.mybank.common.DependencyResolver;
import com.example.mybank.common.MyApplicationContext;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.context.annotation.Profile;

/**
 * todo
 */
@Singleton
@Named("fixedDepositDao")
@Profile("hibernate")
public class FixedDepositHibernateDao implements FixedDepositDao, DependencyResolver {

    private FixedDepositDao fixedDepositDao;

    private final Int2ObjectMap<FixedDepositDetails> fixedDeposits = new Int2ObjectArrayMap<>();

    @Override
    public void resolveDependency(MyApplicationContext context) {
        LOGGER.info("FixedDepositHibernateDao: Resolving dependency for FixedDepositHibernateDao");
        fixedDepositDao = context.getBean(FixedDepositDao.class);
    }

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        return fixedDeposits.get(id);
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        fixedDeposits.put(fixedDepositDetails.getId(), fixedDepositDetails);
        return 1;
    }
}

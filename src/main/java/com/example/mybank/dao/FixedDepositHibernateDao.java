package com.example.mybank.dao;

import com.example.mybank.common.DependencyResolver;
import com.example.mybank.common.MyApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * todo
 */
public class FixedDepositHibernateDao extends FixedDepositDefaultDao implements DependencyResolver {

    private FixedDepositDao fixedDepositDao;

    @Override
    public void resolveDependency(MyApplicationContext context) {
        LOGGER.info("FixedDepositHibernateDao: Resolving dependency for FixedDepositHibernateDao");
        fixedDepositDao = context.getBean(FixedDepositDao.class);
    }

    @Override
    @PostConstruct
    public void initializeDbConnection() throws Exception {
        LOGGER.info("FixedDepositHibernateDao: Initializing");
    }

    @Override
    @PreDestroy
    public void releaseDbConnection() throws Exception {
        LOGGER.info("FixedDepositHibernateDao: Destroyed");
    }
}

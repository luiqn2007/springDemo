package com.example.mybank.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class FixedDepositIbatisDao extends FixedDepositDefaultDao
        implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("FixedDepositIbatisDao: Destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FixedDepositIbatisDao: Initializing");
    }
}

package com.example.mybank.dao;

import com.example.mybank.common.DependencyResolver;
import com.example.mybank.common.MyApplicationContext;
import com.example.mybank.domain.FixedDepositDetails;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

/**
 * todo
 */
@Singleton
@Named("fixedDepositDao")
@Profile("hibernate")
@Transactional
public class FixedDepositHibernateDao implements FixedDepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        String hql = "from FixedDepositDetails where FixedDepositDetails.id=" + id;
        return sessionFactory.getCurrentSession().createQuery(hql, FixedDepositDetails.class).uniqueResult();
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        sessionFactory.getCurrentSession().persist(fixedDepositDetails);
        return fixedDepositDetails.getId();
    }
}

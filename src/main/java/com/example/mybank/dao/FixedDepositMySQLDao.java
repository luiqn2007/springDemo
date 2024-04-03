package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Profile("mysql")
@Repository("fixedDepositDao")
@Transactional
public class FixedDepositMySQLDao implements FixedDepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.id=" + id;
        return sessionFactory.getCurrentSession().createQuery(hql, FixedDepositDetails.class).uniqueResult();
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        sessionFactory.getCurrentSession().persist(fixedDepositDetails);
        return fixedDepositDetails.getId();
    }
}

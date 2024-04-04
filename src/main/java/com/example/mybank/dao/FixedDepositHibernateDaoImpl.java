package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Profile("hibernate")
@Repository("fixedDepositDao")
public class FixedDepositHibernateDaoImpl implements FixedDepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public FixedDepositDetails getFixedDeposit(int id) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.id = " + id;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, FixedDepositDetails.class)
                .uniqueResult();
    }

    @Override
    @Transactional
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        sessionFactory.getCurrentSession().persist(fixedDepositDetails);
        return fixedDepositDetails.getId();
    }
}

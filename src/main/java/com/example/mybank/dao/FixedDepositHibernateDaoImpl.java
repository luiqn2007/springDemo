package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("hibernate")
@Repository("fixedDepositDao")
public class FixedDepositHibernateDaoImpl implements FixedDepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public FixedDepositDetails getFixedDeposit(int id) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.id = " + id;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, FixedDepositDetails.class)
                .uniqueResult();
    }

    @Override
    public int createFixedDetail(FixedDepositDetails fixedDepositDetails) {
        sessionFactory.getCurrentSession().persist(fixedDepositDetails);
        return fixedDepositDetails.getId();
    }

    @Override
    public Iterable<FixedDepositDetails> getHighValueFds(int minValue) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.depositAmount >= :minValue";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, FixedDepositDetails.class)
                .setParameter("minValue", minValue)
                .getResultList();
    }
}

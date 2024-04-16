package com.example.mybank.dao;

import com.example.mybank.domain.FixedDepositDetails;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("hibernate")
@Repository("fixedDepositDao")
public class FixedDepositHibernateDaoImpl implements FixedDepositDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Getter
    private final List<FixedDepositDetails> inactiveFds = new ArrayList<>();

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

    @Override
    public Iterable<FixedDepositDetails> getAllFds(int amount, int tenure) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.depositAmount = :amount and fixedDepositDetails.tenure = :tenure";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, FixedDepositDetails.class)
                .setParameter("amount", amount)
                .setParameter("tenure", tenure)
                .getResultList();
    }

    @Override
    public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
        String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.bankAccountId = :bankAccountId";
        return sessionFactory.getCurrentSession().createQuery(hql, FixedDepositDetails.class)
                .setParameter("bankAccountId", bankAccountId)
                .getResultList();
    }

    @Override
    public List<FixedDepositDetails> getFixedDeposits() {
        String hql = "from FixedDepositDetails";
        return sessionFactory.getCurrentSession().createQuery(hql, FixedDepositDetails.class).getResultList();
    }
}

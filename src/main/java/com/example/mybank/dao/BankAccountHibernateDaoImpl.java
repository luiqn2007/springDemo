package com.example.mybank.dao;

import com.example.mybank.domain.BankAccountDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("bankAccountDao")
@Profile("hibernate")
public class BankAccountHibernateDaoImpl implements BankAccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createBankAccount(BankAccountDetails bankAccountDetails) {
        sessionFactory.getCurrentSession().persist(bankAccountDetails);
    }

    @Override
    public BankAccountDetails getBankAccount(int bankAccountId) {
        String hql = "from BankAccountDetails where BankAccountDetails.accountId = " + bankAccountId;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, BankAccountDetails.class)
                .uniqueResult();
    }

    @Override
    public void subtractAmount(int bankAccountId, float amount) {
        String hql = "from BankAccountDetails where BankAccountDetails.accountId = " + bankAccountId;
        BankAccountDetails bankAccountDetails = sessionFactory.getCurrentSession()
                .createQuery(hql, BankAccountDetails.class)
                .uniqueResult();
        bankAccountDetails.setBalanceAmount(bankAccountDetails.getBalanceAmount() - amount);
        sessionFactory.getCurrentSession().merge(bankAccountDetails);
    }

    @Override
    public boolean isDuplicateAccount(int bankAccountId) {
        String hql = "from BankAccountDetails where BankAccountDetails.accountId = " + bankAccountId;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, BankAccountDetails.class)
                .uniqueResult() != null;
    }
}

package com.example.mybank.dao;

import com.example.mybank.domain.BankAccountDetails;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("bankAccountDao")
@Transactional
public class BankAccountDaoImpl implements BankAccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int createBankAccount(BankAccountDetails bankAccountDetails) {
        sessionFactory.getCurrentSession().persist(bankAccountDetails);
        return bankAccountDetails.getAccountId();
    }

    @Override
    public BankAccountDetails getBankAccount(int bankAccountId) {
        String hql = "from BankAccountDetails where BankAccountDetails.accountId =" + bankAccountId;
        return sessionFactory.getCurrentSession()
                .createQuery(hql, BankAccountDetails.class)
                .uniqueResult();
    }

    @Override
    public void subtractAmount(int bankAccountId, float amount) {
        String hql = "from BankAccountDetails where BankAccountDetails.accountId =" + bankAccountId;
        BankAccountDetails bankAccountDetails = sessionFactory.getCurrentSession()
                .createQuery(hql, BankAccountDetails.class)
                .uniqueResult();
        bankAccountDetails.setBalanceAmount(bankAccountDetails.getBalanceAmount() - amount);
        sessionFactory.getCurrentSession().merge(bankAccountDetails);
    }
}

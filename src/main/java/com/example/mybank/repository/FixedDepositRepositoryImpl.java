package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class FixedDepositRepositoryImpl implements FixedDepositRepositoryCustom {

    private final List<FixedDepositDetails> inactiveFds = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public synchronized List<FixedDepositDetails> getInactiveFds() {
        return List.copyOf(inactiveFds);
    }

    @Override
    public synchronized void addInactiveFixedDeposit(FixedDepositDetails fd) {
        inactiveFds.add(fd);
    }

    @Override
    public synchronized void setFixedDepositAsActive(FixedDepositDetails fd) {
        fd.setActive("Y");
        entityManager.persist(fd);
        inactiveFds.remove(fd);
    }
}

package com.example.mybank.repository;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FixedDepositRepositoryImpl implements FixedDepositRepositoryCustom {

    private final List<FixedDepositDetails> inactiveFds = new ArrayList<>();

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

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
        fixedDepositRepository.save(fd);
        inactiveFds.remove(fd);
    }
}

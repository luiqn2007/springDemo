package com.example.mybank.service;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.domain.QFixedDepositDetails;
import com.example.mybank.repository.FixedDepositQuerydslRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("fixedDepositService")
@Profile("querydsl")
public class FixedDepositServiceQuerydslImpl implements FixedDepositService {

    @Autowired
    private FixedDepositQuerydslRepository fixedDepositRepository;

    @Override
    public FixedDepositDetails getFixedDepositDetails(int id) {
        Predicate predicate = QFixedDepositDetails.fixedDepositDetails.id.eq(id);
        return fixedDepositRepository.findOne(predicate).orElseThrow();
    }

    @Override
    public int createFixedDeposit(FixedDepositDetails fixedDepositDetails) {
        return fixedDepositRepository.save(fixedDepositDetails).getId();
    }

    @Override
    public Iterable<FixedDepositDetails> getHighValueFds(int minValue) {
        Predicate predicate = QFixedDepositDetails.fixedDepositDetails.active.eq("Y")
                .and(QFixedDepositDetails.fixedDepositDetails.depositAmount.goe(minValue));
        return fixedDepositRepository.findAll(predicate);
    }
}

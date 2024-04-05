package com.example.mybank.service;

import com.example.mybank.base.ServiceTemplate;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fixedDepositService")
@Profile("jpa")
public class FixedDepositServiceJpaDataImpl extends ServiceTemplate implements FixedDepositService {

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Override
    @Transactional
    public FixedDepositDetails getFixedDepositDetails(int id) {
        return fixedDepositRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public int createFixedDeposit(FixedDepositDetails fixedDepositDetails) {
        return fixedDepositRepository.save(fixedDepositDetails).getId();
    }

    @Override
    @Transactional
    public Iterable<FixedDepositDetails> getHighValueFds(int minValue) {
        return fixedDepositRepository.findAllByDepositAmountGreaterThanEqual(minValue);
    }

    @Override
    public Iterable<FixedDepositDetails> getAllFds(int amount, int tenure) {
        return fixedDepositRepository.findAllByDepositAmountAndTenure(amount, tenure);
    }
}

package com.example.mybank.service;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.domain.QFixedDepositDetails;
import com.example.mybank.repository.FixedDepositQuerydslRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Iterable<FixedDepositDetails> getAllFds(int amount, int tenure) {
        FixedDepositDetails example = FixedDepositDetails.builder()
                .active("Y")
                .depositAmount(amount)
                .tenure(tenure)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<FixedDepositDetails> fdExample = Example.of(example, matcher);
        return fixedDepositRepository.findAll(fdExample);
    }

    @Override
    public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
        return fixedDepositRepository.findFixedDepositDetailsByBankAccountId(BankAccountDetails.builder().accountId(bankAccountId).build());
    }

    @Override
    public List<FixedDepositDetails> getFixedDeposits() {
        return fixedDepositRepository.findAll();
    }
}

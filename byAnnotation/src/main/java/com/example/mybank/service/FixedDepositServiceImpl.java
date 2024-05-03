package com.example.mybank.service;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.domain.QFixedDepositDetails;
import com.example.mybank.repository.FixedDepositRepository;
import com.querydsl.core.types.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {

    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Override
    public FixedDepositDetails getFixedDepositDetails(int id) {
        Predicate predicate = QFixedDepositDetails.fixedDepositDetails.id.eq(id);
        return fixedDepositRepository.findOne(predicate).orElseThrow();
    }

    @Override
    public void createFixedDeposit(FixedDepositDetails fixedDepositDetails) {
        FixedDepositDetails details = fixedDepositRepository.save(fixedDepositDetails);
        LOGGER.info("Saved info {}", details);
    }

    @Override
    public void deleteFixedDeposit(int id) {
        fixedDepositRepository.deleteById(id);
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
                .depositAmount((long) amount)
                .tenure(tenure)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<FixedDepositDetails> fdExample = Example.of(example, matcher);
        return fixedDepositRepository.findAll(fdExample);
    }

    @Override
    public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
        return fixedDepositRepository.findAllByBankAccountId(BankAccountDetails.builder().accountId(bankAccountId).build());
    }

    @Override
    public List<FixedDepositDetails> getFixedDeposits() {
        return fixedDepositRepository.findAll();
    }
}

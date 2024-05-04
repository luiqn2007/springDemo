package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;
import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.domain.QFixedDepositDetails;
import com.example.mybank_xml.respository.FixedDepositRepository;
import com.querydsl.core.types.Predicate;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

@Setter
public class FixedDepositServiceImpl implements FixedDepositService {

    private final static Logger LOGGER = LogManager.getLogger();

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

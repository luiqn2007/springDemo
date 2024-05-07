package com.example.mybank_xml.service;

import com.example.mybank_xml.domain.BankAccountDetails;
import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.domain.QFixedDepositDetails;
import com.example.mybank_xml.respository.FixedDepositRepository;
import com.querydsl.core.types.Predicate;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;

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

    @Override
    public void editFixedDeposit(FixedDepositDetails fixedDepositDetails) {
        fixedDepositRepository.save(fixedDepositDetails);
    }

    @Autowired
    private MutableAclService mutableAclService;

    @Override
    public void provideAccessToAdmin(int fixedDepositId) {
        addPermission(fixedDepositId, new PrincipalSid("admin"), BasePermission.READ);
        addPermission(fixedDepositId, new PrincipalSid("admin"), BasePermission.ADMINISTRATION);
        addPermission(fixedDepositId, new PrincipalSid("admin"), BasePermission.DELETE);
    }

    @Override
    public void closeFixedDeposit(int fixedDepositId) {
        fixedDepositRepository.closeFixedDeposit(fixedDepositId);
        ObjectIdentity old = new ObjectIdentityImpl(FixedDepositDetails.class, fixedDepositId);
        mutableAclService.deleteAcl(old, false);
    }

    private void addPermission(int fixedDepositId, Sid recipient, Permission permission) {
        MutableAcl acl;
        ObjectIdentity old = new ObjectIdentityImpl(FixedDepositDetails.class, fixedDepositId);

        try {
            acl = (MutableAcl) mutableAclService.readAclById(old);
        } catch (NotFoundException e) {
            acl = mutableAclService.createAcl(old);
        }

        acl.insertAce(acl.getEntries().size(), permission, recipient, true);
        mutableAclService.updateAcl(acl);
    }
}

package com.example.mybank.service;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface FixedDepositService {

    @PreAuthorize("hasPermission(#id, 'com.example.mybank.domain.FixedDepositDetails', read) or " +
                  "hasPermission(#id, 'com.example.mybank.domain.FixedDepositDetails', admin)")
    FixedDepositDetails getFixedDepositDetails(int id);

    void createFixedDeposit(FixedDepositDetails fixedDepositDetails);

    void deleteFixedDeposit(int id);

    Iterable<FixedDepositDetails> getHighValueFds(int minValue);

    Iterable<FixedDepositDetails> getAllFds(int amount, int tenure);

    List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId);

    @PreAuthorize("hasRole('ROLE_CUSTOM')")
    @PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
    List<FixedDepositDetails> getFixedDeposits();

    @PreAuthorize("hasPermission(#fixedDepositDetails, write)")
    void editFixedDeposit(FixedDepositDetails fixedDepositDetails);

    @PreAuthorize("hasPermission(#fixedDepositId, 'com.example.mybank.domain.FixedDepositDetails', write)")
    void provideAccessToAdmin(int fixedDepositId);

    @PreAuthorize("hasPermission(#fixedDepositId, 'com.example.mybank.domain.FixedDepositDetails', write) and hasRole('ROLE_ADMIN')")
    void closeFixedDeposit(int fixedDepositId);
}

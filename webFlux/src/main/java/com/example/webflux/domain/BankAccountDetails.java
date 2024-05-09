package com.example.webflux.domain;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"balance", "lastTransactionTimestamp", "fixedDeposits", "count", "findOne"})
@ToString
@Document(collection = "bankaccounts")
public class BankAccountDetails {
    @Id
    private String accountId;

    private int balance;

    private Date lastTransactionTimestamp;

    private List<FixedDepositDetails> fixedDeposits = new ArrayList<>();

    private long count;

    private long findOne;
}

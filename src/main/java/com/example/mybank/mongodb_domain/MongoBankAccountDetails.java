package com.example.mybank.mongodb_domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "bank_accounts")
public class MongoBankAccountDetails {

    @Id
    private String accountId;
    private float balance;
    private Date lastTransactionTimestamp;
    private List<MongoFixedDepositDetails> fixedDeposits;
}

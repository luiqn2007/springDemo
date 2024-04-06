package com.example.mybank.mongodb_domain;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bank_accounts")
@Entity
public class MongoBankAccountDetails {

    @Id
    private String accountId;
    private float balance;
    private Date lastTransactionTimestamp;
    private List<MongoFixedDepositDetails> fixedDeposits;
}

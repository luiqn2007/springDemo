package com.example.mybank.chapter03.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixedDepositDetails {

    private long id;
    private float depositAmount;
    private int tenure;
    private String email;
}

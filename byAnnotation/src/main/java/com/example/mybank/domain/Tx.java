package com.example.mybank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Tx {

    private int id;
    String type, status;
}

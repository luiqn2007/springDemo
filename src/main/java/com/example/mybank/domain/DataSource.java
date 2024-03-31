package com.example.mybank.domain;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class DataSource {

    private String url;

    private String driverClass;

    private String username;

    private String password;
}

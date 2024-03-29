package com.example.mybank.domain;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class DataSource {
    String url, driverClass, username, password;
}

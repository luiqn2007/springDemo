package com.example.mybank.spel;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Component("sample")
public class SpELSample {

    @Value("Some currency")
    private String currency;

    @Value("#{testEnvironment}")
    private EnvironmentBean env;

    @Autowired
    @Qualifier("testEnvironment")
    private EnvironmentBean env2;

    @Value("#{testEnvironment.environment}")
    private String environment;

    @Value("#{testEnvironment.getCity()}")
    private String city;

    @Value("#{testEnvironment.splitName('AAA bbb')}")
    private String[] splitName;

    @Value("#{101 > 100}")
    private boolean isGreaterThan;

    @Value("#{3 > 2 && 4 > 3}")
    private boolean isConditionTrue;

    @Value("#{100 + 200 - 300 + 4 / 2}")
    private int totalAmount;

    @Value("#{'abcd@xyz.com' matches '^[A-Za-z0-9+-_.]+@(.+)$'}")
    private boolean isEmail;

//    @Value("#{listType[0]}")
//    private String listItem;

//    @Value("#{mapType['key2']}")
//    private String mapItem;

    @Value("#{T(java.util.UUID).randomUUID()}")
    private UUID uuid;
}

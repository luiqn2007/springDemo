package com.example.mybank.bean;

import lombok.Data;

import java.util.*;

@Data
public class DataTypesExample {

    String stringType;
    byte[] byteArray;
    char[] charArray;
    char charType;
    Class<?> classType;
    Currency currencyType;
    boolean booleanType;
    Date dateType;
    long longType;
    double doubleType;
    Properties propertiesType;

    List<String> listType;
    Map<String, Object> mapType;
    Set<Integer> setType;
    Properties anotherPropertiesType;
    int[] arrayType;

    Object nullType = new Object();

    public boolean getBooleanType() {
        return booleanType;
    }
}

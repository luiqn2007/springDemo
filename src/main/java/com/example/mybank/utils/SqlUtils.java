package com.example.mybank.utils;

import org.springframework.jdbc.support.KeyHolder;

public class SqlUtils {

    public static int getGeneratedId(KeyHolder keyHolder) {
        Object generated_key = keyHolder.getKeys().get("GENERATED_KEY");
        if (generated_key instanceof Number id) {
            return id.intValue();
        } else {
            throw new RuntimeException("Unable to get generated id");
        }
    }
}

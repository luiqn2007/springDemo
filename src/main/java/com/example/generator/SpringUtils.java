package com.example.generator;

public class SpringUtils {

    public static String camelName(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}

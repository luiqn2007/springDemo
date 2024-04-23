package com.example.mybank.spel;

public abstract class SpringUtil {

    public static String reverseString(String str) {
        StringBuilder backwards = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            backwards.append(str.charAt(i));
        }
        return backwards.toString();
    }
}

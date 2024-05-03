package com.example.mybank_xml;

import org.springframework.web.context.support.XmlWebApplicationContext;

public class BankApp {

    public static void main(String[] args) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.start();
    }
}

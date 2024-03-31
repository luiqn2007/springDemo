package com.example.mybank.utils;

public class DatabaseConnection {

    public static DatabaseConnection getInstance() {
        return new DatabaseConnection();
    }

    private DatabaseConnection() {
    }

    public void releaseConnection() {
    }
}

package com.example.mybank.dao;

public class DatabaseConnection {

    public static DatabaseConnection getInstance() {
        return new DatabaseConnection();
    }

    private DatabaseConnection() {
    }

    public void releaseConnection() {
    }
}

package com.example.mybank;

public class DatabaseConnection {

    public static DatabaseConnection getInstance() {
        return new DatabaseConnection();
    }

    public void releaseConnection() {
    }
}

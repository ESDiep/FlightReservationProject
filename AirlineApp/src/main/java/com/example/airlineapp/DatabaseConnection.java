package com.example.airlineapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getDBConnection() throws SQLException {
	databaseLink = DriverManager.getConnection("jdbc:mysql://localhost:3306/AirlineApp", "root", "1234");
	System.out.println("Connected to MySQL Database!");
	return databaseLink;
    }
}

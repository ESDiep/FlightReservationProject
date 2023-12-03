package com.example.airlineapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getDBConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        // FileInputStream fis = new FileInputStream("app.properties");
        InputStream inputStream = getClass().getResourceAsStream("app.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("Property file 'app.properties' not found in the classpath");
        }
        properties.load(inputStream);
        // properties.load(fis);
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        databaseLink = DriverManager.getConnection(url, username, password);
	    System.out.println("Connected to MySQL Database!");
	    return databaseLink;
    }
}

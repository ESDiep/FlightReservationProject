package com.example.airlineapp;

//import com.example.airlineapp.DatabaseConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Statement;
import java.text.ParseException;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
public class FillDatabase {
    static DatabaseConnection connectionObject;
//    private String[]=new Strin

    FillDatabase(){
        connectionObject = new DatabaseConnection();
    }

    public void createDB() throws SQLException, FileNotFoundException {
        try (Connection connection = connectionObject.getDBConnection()){
            try (BufferedReader reader = new BufferedReader(new FileReader("AirlineApp.sql"))){
                String airplaneDB= """
    DROP DATABASE IF EXISTS airlineapp;
    CREATE DATABASE airlineapp;
    use airlineapp;
    
    DROP TABLE IF EXISTS flights;
    CREATE TABLE flights (
        flightID			varchar(45),
        depart_time			varchar(45) not null,
        arrival_time		varchar(45) not null,
        origin		varchar(45) not null,
        destination		varchar(45) not null,
        flightdate		varchar(45) not null,
        aircraft		varchar(45) not null,
        primary key (flightID)
    );
    DROP TABLE IF EXISTS payments;
    CREATE TABLE `AirlineApp`.`payments` (
    `paymentID` INT NOT NULL,
    `cardholdername` VARCHAR(45) NOT NULL,
    `cardnumber` INT NOT NULL,
    `securitycode` INT NOT NULL,
    PRIMARY KEY (`paymentID`));
    
    DROP TABLE IF EXISTS tickets;
    CREATE TABLE `AirlineApp`.`tickets` (
      `ticketID` INT NOT NULL,
      `paymentID` INT NOT NULL,
      `flightID` VARCHAR(45) NOT NULL,
      `cust_lastname` VARCHAR(45) NOT NULL,
      `cust_firstname` VARCHAR(45) NOT NULL,
      `seatID` VARCHAR(45) NOT NULL,
      `price` float NOT NULL,
      `depart_time` VARCHAR(45) NOT NULL,
      `arrival_time` VARCHAR(45) NOT NULL,
      `originOutput` VARCHAR(45) NOT NULL,
      `destinationOutput` VARCHAR(45) NOT NULL,
      `flightdate` VARCHAR(45) NOT NULL,
      PRIMARY KEY (`ticketID`),
      foreign key (flightID) REFERENCES airlineapp.flights(flightID) on update cascade ON DELETE CASCADE,
      foreign key (paymentID) REFERENCES airlineapp.payments(paymentID) on update cascade ON DELETE CASCADE,
      UNIQUE INDEX `ticketID_UNIQUE` (`ticketID` ASC) VISIBLE);
    
    DROP TABLE IF EXISTS users;
    CREATE TABLE airlineapp.users (
        UserID INT PRIMARY KEY,
        FirstName VARCHAR(50) NOT NULL ,
        LastName VARCHAR(50) NOT NULL ,
        Address VARCHAR(300) NOT NULL,
        Email VARCHAR(300) NOT NULL,
        Username VARCHAR(50) NOT NULL,
        Password VARCHAR(50) NOT NULL,
        UserType VARCHAR(20) NOT NULL
        );
           """;
                Statement statement = connection.createStatement();
                String line;
                while ((line = reader.readLine()) != null) {
                    statement.execute(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public void fillDatabase() throws SQLException {

        try (Connection connection = connectionObject.getDBConnection()) {
//            truncateTable("flights");
            generateAndInsertFlights(100);
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void generateAndInsertFlights(int numberOfFlights) throws SQLException, ParseException {
        try (Connection connection = connectionObject.getDBConnection()) {
            String insertQuery = "INSERT INTO flights (flightID, depart_time, arrival_time, origin, destination, flightdate, aircraft) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (int i = 1; i <= numberOfFlights; i++) {
                    String flightID = "FL" + String.format("%03d", i);
                    String departTime = generateRandomTime();
                    String arrivalTime = generateRandomTimeAfter(departTime,1);
                    String origin = generateRandomCanadianCity();
                    String destination = generateRandomCanadianCityOtherThan(origin);
                    String flightDate = generateRandomDate();
                    String aircraft = generateRandomAircraft();

                    preparedStatement.setString(1, flightID);
                    preparedStatement.setString(2, departTime);
                    preparedStatement.setString(3, arrivalTime);
                    preparedStatement.setString(4, origin);
                    preparedStatement.setString(5, destination);
                    preparedStatement.setString(6, flightDate);
                    preparedStatement.setString(7, aircraft);

                    preparedStatement.executeUpdate();
                }
            }
        }
    }
//    public static void truncateTable(String tableName) throws SQLException {
//
//        try (Connection connection = connectionObject.getDBConnection()) {
//            String resetQuery="TRUNCATE TABLE " + tableName;
//            try (PreparedStatement preparedStatement = connection.prepareStatement(resetQuery)){
//                preparedStatement.executeUpdate();
//            }
//        }
//    }

    private static String generateRandomCanadianCity() {
        String[] canadianCities = {"Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Ottawa", "Quebec City"};
        return canadianCities[(int) (Math.random() * canadianCities.length)];
    }

    private static String generateRandomCanadianCityOtherThan(String city) {
        String[] canadianCities = {"Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Ottawa", "Quebec City"};
        String randomCity;
        do {
            randomCity = canadianCities[(int) (Math.random() * canadianCities.length)];
        } while (randomCity.equals(city));
        return randomCity;
    }

    private static String generateRandomAircraft() {
        String[] aircraftTypes = {"Boeing 747", "Airbus A321"};
        return aircraftTypes[(int) (Math.random() * aircraftTypes.length)];
    }

    private static String generateRandomTime() {
        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        return String.format("%02d:%02d", hour, minute);
    }

    private static String generateRandomTimeAfter(String startTime, int hoursToAdd) {
        String[] parts = startTime.split(":");
        int startHour = Integer.parseInt(parts[0]);
        int startMinute = Integer.parseInt(parts[1]);

        int totalMinutes = startHour * 60 + startMinute + hoursToAdd * 60;

        int arrivalHour = totalMinutes / 60;
        int arrivalMinute = totalMinutes % 60;

        return String.format("%02d:%02d", arrivalHour, arrivalMinute);
    }
    private static String generateRandomDate(){
        String date="2023-12-";
        int day = (int) (Math.random() * (31 - 25 + 1)) + 25;
        String randomDay = String.valueOf(day);
        date+=randomDay;
        return date;
    }
}

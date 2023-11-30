DROP DATABASE IF EXISTS FlightReservation;
CREATE DATABASE FlightReservation;
USE FlightReservation;

DROP TABLE IF EXISTS User;
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL ,
    LastName VARCHAR(50) NOT NULL ,
    Address VARCHAR(300) NOT NULL,
    Email VARCHAR(300) NOT NULL,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    UserType VARCHAR(20) NOT NULL);

DROP TABLE IF EXISTS Flights;
CREATE TABLE Flights (
    FlightID INT PRIMARY KEY,
    FlightNumber VARCHAR(20) NOT NULL,
    Origin VARCHAR(50) NOT NULL,
    Destination VARCHAR(50) NOT NULL,
    DepartureDateTime DATETIME NOT NULL,
    ArrivalDateTime DATETIME NOT NULL,
    AvailableSeats INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    AircraftID INT,
    FOREIGN KEY (AircraftID) REFERENCES Aircraft(AircraftID) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Aircraft;
CREATE TABLE Aircraft (
    AircraftID INT PRIMARY KEY,
    AircraftType VARCHAR(50) NOT NULL,
    RegistrationNumber INT NOT NULL
);

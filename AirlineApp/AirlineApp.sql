DROP DATABASE IF EXISTS airlineapp;
CREATE DATABASE airlineapp;
use airlineapp;
# select * from users;
# select * from flights;
# select * from tickets;
# select * from payments;

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
    UserType VARCHAR(20) NOT NULL);
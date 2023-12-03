DROP DATABASE IF EXISTS airlineapp;
CREATE DATABASE airlineapp;
use airlineapp;

DROP TABLE IF EXISTS users;
CREATE TABLE `AirlineApp`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `usertype` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `discountcode` VARCHAR(45) NULL,
  PRIMARY KEY (`username`));
  
DROP TABLE IF EXISTS flights;
CREATE TABLE flights (
	flightID			varchar(45) not null,
	depart_time			varchar(45) not null,
	arrival_time		varchar(45) not null,
    origin		varchar(45) not null,
    destination		varchar(45) not null,
    flightdate		varchar(45) not null,
    aircraft		varchar(45) not null,
	primary key (flightID)
);
-- INSERT INTO flights (flightID, depart_time, arrival_time,origin,destination,flightdate,aircraft)
-- VALUES
-- ('FL001',	'11:35',	'14:34','calgary','toronto','2023-12-25','boeing 747'),
-- ('FL002',	'12:35',	'15:34','calgary','toronto','2023-12-25','airbus a321'),
-- ('FL003',	'13:35',	'16:34','calgary','toronto','2023-12-25','boeing a320'),
-- ('FL004',	'14:35',	'17:34','calgary','toronto','2023-12-25','boeing 737');

DROP TABLE IF EXISTS tickets;
CREATE TABLE `AirlineApp`.`tickets` (
  `ticketID` INT NOT NULL,
  `paymentID` INT NOT NULL,
  `flightID` VARCHAR(45) NOT NULL,
  `cust_lastname` VARCHAR(45) NOT NULL,
  `cust_firstname` VARCHAR(45) NOT NULL,
  `seatID` VARCHAR(45) NOT NULL,
  `price` float NOT NULL,
  `aircraft` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `depart_time` VARCHAR(45) NOT NULL,
  `arrival_time` VARCHAR(45) NOT NULL,
  `originOutput` VARCHAR(45) NOT NULL,
  `destinationOutput` VARCHAR(45) NOT NULL,
  `flightdate` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ticketID`),
  UNIQUE INDEX `ticketID_UNIQUE` (`ticketID` ASC) VISIBLE); 
  
  DROP TABLE IF EXISTS payments;
  CREATE TABLE `AirlineApp`.`payments` (
  `paymentID` INT NOT NULL,
  `cardholdername` VARCHAR(45) NOT NULL,
  `cardnumber` VARCHAR(45) NOT NULL,
  `securitycode` INT NOT NULL,
  PRIMARY KEY (`paymentID`));
  
select * from users;
select * from flights;
select * from tickets;
select * from payments;
  
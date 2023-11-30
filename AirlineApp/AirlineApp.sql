use airlineapp;


DROP TABLE IF EXISTS flights;
CREATE TABLE flights (
	flightID			varchar(45) not null,
	depart_time			varchar(45) not null,
	arrival_time		varchar(45) not null,
    origin				varchar(45) not null,
    destination			varchar(45) not null,
    flightdate			varchar(45) not null,
    aircraft			varchar(45) not null,
   # capacity 			int,
   # seatBooked			int,
   # seatEmpty			int,
	primary key (flightID)
);
INSERT INTO flights (flightID, depart_time, arrival_time,origin,destination,flightdate,aircraft)
VALUES
('FL001',	'11:35',	'14:34','calgary','toronto','2023-12-25','boeing 747'),
('FL002',	'12:35',	'15:34','calgary','toronto','2023-12-25','airbus a321'),
('FL003',	'13:35',	'16:34','calgary','toronto','2023-12-25','boeing 747'),
('FL004',	'14:35',	'17:34','calgary','vancouver','2023-12-25','boeing a321'),

('FL005',	'11:35',	'14:34','calgary','vancouver','2023-12-26','boeing 747'),
('FL006',	'12:35',	'15:34','calgary','vancouver','2023-12-26','airbus a321'),

('FL007',	'13:35',	'16:34','calgary','toronto','2023-12-27','boeing 747'),
('FL008',	'14:35',	'17:34','calgary','toronto','2023-12-27','boeing a321'),

('FL009',	'14:35',	'17:34','calgary','vancouver','2023-12-28','boeing a321'),
('FL010',	'11:35',	'14:34','calgary','vancouver','2023-12-28','boeing 747'),
('FL011',	'12:35',	'15:34','calgary','vancouver','2023-12-28','airbus a321'),

('FL012',	'14:35',	'17:34','calgary','vancouver','2023-12-29','boeing a321'),
('FL013',	'11:35',	'14:34','calgary','vancouver','2023-12-29','boeing 747'),
('FL014',	'12:35',	'15:34','calgary','vancouver','2023-12-29','airbus a321');

SELECT depart_time, arrival_time,origin,destination,flightdate,aircraft FROM flights WHERE flightID ='FL002';

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
  UNIQUE INDEX `ticketID_UNIQUE` (`ticketID` ASC) VISIBLE); 
  
  DROP TABLE IF EXISTS payments;
  CREATE TABLE `AirlineApp`.`payments` (
  `paymentID` INT NOT NULL,
  `cardholdername` VARCHAR(45) NOT NULL,
  `cardnumber` INT NOT NULL,
  `securitycode` INT NOT NULL,
  PRIMARY KEY (`paymentID`));
  
ALTER TABLE tickets
ADD COLUMN aircraft VARCHAR(45),
ADD COLUMN email VARCHAR(45);

INSERT INTO tickets (ticketID, depart_time, arrival_time,origin,destination,flightdate,aircraft)
VALUES
('FL001',	'11:35',	'14:34','calgary','toronto','2023-12-25','boeing 747'),
('FL002',	'12:35',	'15:34','calgary','toronto','2023-12-25','airbus a321'),
('FL003',	'13:35',	'16:34','calgary','toronto','2023-12-25','boeing 747'),
('FL004',	'14:35',	'17:34','calgary','vancouver','2023-12-25','boeing a321'),

('FL005',	'11:35',	'14:34','calgary','vancouver','2023-12-26','boeing 747'),
('FL006',	'12:35',	'15:34','calgary','vancouver','2023-12-26','airbus a321'),

('FL007',	'13:35',	'16:34','calgary','toronto','2023-12-27','boeing 747'),
('FL008',	'14:35',	'17:34','calgary','toronto','2023-12-27','boeing a321'),

('FL009',	'14:35',	'17:34','calgary','vancouver','2023-12-28','boeing a321'),
('FL010',	'11:35',	'14:34','calgary','vancouver','2023-12-28','boeing 747'),
('FL011',	'12:35',	'15:34','calgary','vancouver','2023-12-28','airbus a321'),

('FL012',	'14:35',	'17:34','calgary','vancouver','2023-12-29','boeing a321'),
('FL013',	'11:35',	'14:34','calgary','vancouver','2023-12-29','boeing 747'),
('FL014',	'12:35',	'15:34','calgary','vancouver','2023-12-29','airbus a321');

#select * from users;
select * from flights;
select * from tickets;
select * from payments;
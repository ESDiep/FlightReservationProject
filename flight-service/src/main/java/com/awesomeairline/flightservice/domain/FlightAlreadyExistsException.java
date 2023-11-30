package com.awesomeairline.flightservice.domain;

public class FlightAlreadyExistsException extends RuntimeException {
    public FlightAlreadyExistsException(String flightNumber) {
        super("Flight with flight number " + flightNumber + " already exists.");
    }
}

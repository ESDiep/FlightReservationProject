package com.awesomeairline.flightservice.domain;

public class AircraftTypeAlreadyExistsException extends RuntimeException {
    public AircraftTypeAlreadyExistsException(String name) {
        super("Aircraft type with name " + name + " already exists.");
    }
}

package com.awesomeairline.flightservice.domain;

public class AircraftTypeNotFoundException extends RuntimeException {
    public AircraftTypeNotFoundException(String name) {
        super("Aircraft type with name " + name + " not found.");
    }
}

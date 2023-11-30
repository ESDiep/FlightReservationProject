package com.awesomeairline.flightservice.domain;

import java.util.Optional;

public interface FlightRepository {
    Iterable<Flight> findAll();
    Optional<Flight> findByFlightNumber(String flightNumber);
    boolean existsByFlightNumber(String flightNumber);
    Flight save(Flight flight);
    void deleteByFlightNumber(String flightNumber);
}

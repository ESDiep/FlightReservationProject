package com.awesomeairline.flightservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.awesomeairline.flightservice.domain.Flight;
import com.awesomeairline.flightservice.domain.FlightRepository;

@Repository
public class InMemoryFlightRepository implements FlightRepository {
    private static final Map<String, Flight> flights = new ConcurrentHashMap<>();
    
    @Override
    public Iterable<Flight> findAll() {
        return flights.values();
    }

    @Override
    public boolean existsByFlightNumber(String flightNumber) {
        return flights.get(flightNumber) != null;
    }

    @Override
    public Flight save(Flight flight) {
        flights.put(flight.flightNumber(), flight);
        return flight;
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flights.remove(flightNumber);
    }

    @Override
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return existsByFlightNumber(flightNumber) ? Optional.of(flights.get(flightNumber)) : Optional.empty();
    }

}

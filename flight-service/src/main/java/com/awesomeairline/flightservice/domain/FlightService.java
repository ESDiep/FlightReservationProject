package com.awesomeairline.flightservice.domain;

import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight viewFlightDetails(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
            .orElseThrow(() -> new FlightNotFoundException(flightNumber));
    }

    public Flight addFlight(Flight flight) {
        if (flightRepository.existsByFlightNumber(flight.flightNumber())) {
            throw new FlightAlreadyExistsException(flight.flightNumber());
        }
        return flightRepository.save(flight);
    }

    public void deleteFlight(String flightNumber) {
        flightRepository.deleteByFlightNumber(flightNumber);
    }

    public Flight updateFlight(String flightNumber, Flight flight) {
        return flightRepository.findByFlightNumber(flightNumber)
            .map(existingFlight -> {
                var flightToUpdate = new Flight(
                    flightNumber,
                    flight.origin(),
                    flight.destination(),
                    flight.departureDate(),
                    flight.departureTime(),
                    flight.arrivalTime(),
                    flight.aircraftType(),
                    flight.duration(),
                    flight.status()
                );
                return flightRepository.save(flightToUpdate);
            })
            .orElseGet(() -> addFlight(flight));
    }
}

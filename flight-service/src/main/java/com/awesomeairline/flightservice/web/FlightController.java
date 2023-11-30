package com.awesomeairline.flightservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.awesomeairline.flightservice.domain.Flight;
import com.awesomeairline.flightservice.domain.FlightService;

@RestController
@RequestMapping("flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public Iterable<Flight> get() {
        return flightService.getAllFlights();
    }

    @GetMapping("{flightNumber}")
    public Flight getByFlightFlightNumber(@PathVariable String flightNumber) {
        return flightService.viewFlightDetails(flightNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight post(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
}

package com.awesomeairline.flightservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Flight(
    @NotBlank(message = "Flight number is required")
    @Pattern(
        regexp = "^[A-Z]{2}\\d{3}$",
        message = "Flight number must be in the format XX000"
    )
    String flightNumber,
    
    @NotBlank(message = "Origin is required")
    String origin,

    @NotBlank(message = "Destination is required")
    String destination,

    @NotBlank(message = "Departure date is required")
    String departureDate,

    @NotBlank(message = "Departure time is required")
    String departureTime,

    @NotBlank(message = "Arrival time is required")
    String arrivalTime,

    @NotBlank(message = "Aircraft type is required")
    String aircraftType,

    @NotBlank(message = "Duration is required")
    String duration,

    @NotBlank(message = "Status is required")
    String status
){}

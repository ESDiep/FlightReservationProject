package com.awesomeairline.flightservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AircraftType(
    @NotBlank(message = "Name is required")
    String name,

    // @NotNull(message = "Capacity is required")
    int capacity,

    @NotNull(message = "Range is required")
    int range,

    @NotNull(message = "Top speed is required")
    int topSpeed
){}

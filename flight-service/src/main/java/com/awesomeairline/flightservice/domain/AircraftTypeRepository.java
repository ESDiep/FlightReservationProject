package com.awesomeairline.flightservice.domain;

import java.util.Optional;

public interface AircraftTypeRepository {
    Iterable<AircraftType> findAll();
    boolean existsByName(String name);
    Optional<AircraftType> findByName(String name);
    AircraftType save(AircraftType aircraftType);
    void deleteByName(String name);
}

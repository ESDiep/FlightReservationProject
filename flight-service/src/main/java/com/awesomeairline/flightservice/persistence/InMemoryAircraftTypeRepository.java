package com.awesomeairline.flightservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.awesomeairline.flightservice.domain.AircraftType;
import com.awesomeairline.flightservice.domain.AircraftTypeRepository;

@Repository
public class InMemoryAircraftTypeRepository implements AircraftTypeRepository {
    private static final Map<String, AircraftType> aircraftTypes = new ConcurrentHashMap<>();

    @Override
    public Iterable<AircraftType> findAll() {
        return aircraftTypes.values();
    }

    @Override
    public boolean existsByName(String name) {
        return aircraftTypes.containsKey(name);
    }

    @Override
    public AircraftType save(AircraftType aircraftType) {
        aircraftTypes.put(aircraftType.name(), aircraftType);
        return aircraftType;
    }

    @Override
    public void deleteByName(String name) {
        aircraftTypes.remove(name);
    }

    @Override
    public Optional<AircraftType> findByName(String name) {
        return aircraftTypes.get(name) != null ? Optional.of(aircraftTypes.get(name)) : Optional.empty();
    }
}

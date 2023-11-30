package com.awesomeairline.flightservice.domain;

import org.springframework.stereotype.Service;

@Service
public class AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;

    public AircraftTypeService(AircraftTypeRepository aircraftTypeRepository) {
        this.aircraftTypeRepository = aircraftTypeRepository;
    }

    public Iterable<AircraftType> getAllAircraftTypes() {
        return aircraftTypeRepository.findAll();
    }

    public AircraftType viewAircraftTypeDetails(String name) {
        return aircraftTypeRepository.findByName(name)
            .orElseThrow(() -> new AircraftTypeNotFoundException(name));
    }

    public AircraftType addAircraftType(AircraftType aircraftType) {
        if (aircraftTypeRepository.existsByName(aircraftType.name())) {
            throw new AircraftTypeAlreadyExistsException(aircraftType.name());
        }
        return aircraftTypeRepository.save(aircraftType);
    }

    public void deleteAircraftType(String name) {
        aircraftTypeRepository.deleteByName(name);
    }

    public AircraftType updateAircraftType(String name, AircraftType aircraftType) {
        return aircraftTypeRepository.findByName(name)
            .map(existingAircraftType -> {
                var aircraftTypeToUpdate = new AircraftType(
                    name,
                    aircraftType.capacity(),
                    aircraftType.range(),
                    aircraftType.topSpeed()
                );
                return aircraftTypeRepository.save(aircraftTypeToUpdate);
            })
            .orElseGet(() -> addAircraftType(aircraftType));
    }
}

package com.awesomeairline.flightservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.awesomeairline.flightservice.domain.AircraftType;
import com.awesomeairline.flightservice.domain.AircraftTypeService;

@RestController
@RequestMapping("aircraft-types")
public class AircraftTypeController {
    private final AircraftTypeService aircraftTypeService;
    
    public AircraftTypeController(AircraftTypeService aircraftTypeService) {
        this.aircraftTypeService = aircraftTypeService;
    }

    @GetMapping
    public Iterable<com.awesomeairline.flightservice.domain.AircraftType> get() {
        return aircraftTypeService.getAllAircraftTypes();
    }

    @GetMapping("{typeName}")
    public com.awesomeairline.flightservice.domain.AircraftType getByAircraftTypeName(String typeName) {
        return aircraftTypeService.viewAircraftTypeDetails(typeName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public com.awesomeairline.flightservice.domain.AircraftType post(@RequestBody AircraftType aircraftType) {
        return aircraftTypeService.addAircraftType(aircraftType);
    }
}

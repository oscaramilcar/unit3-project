package org.kodigo.project.controllers;

import lombok.AllArgsConstructor;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.persistence.IAircraftFileSerializer;
import org.kodigo.project.persistence.IAircraftRepository;

import java.io.IOException;

@AllArgsConstructor
public class AircraftController {
    private final IAircraftRepository aircraftRepository;
    private final IAircraftFileSerializer aircraftFileSerializer;

    public void getAircraft() throws IOException {
        aircraftRepository.findAll();
    }

    public void saveAircraft(Aircraft aircraft) throws IOException {
        Aircraft serialize = aircraftFileSerializer.serialize(aircraft);
        aircraftRepository.save(serialize);
    }

    public void importData(){

    }
}

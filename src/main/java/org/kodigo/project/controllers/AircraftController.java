package org.kodigo.project.controllers;

import lombok.AllArgsConstructor;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;
import org.kodigo.project.persistence.IAircraftFileSerializer;
import org.kodigo.project.persistence.IAircraftRepository;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class AircraftController {
    private final IAircraftRepository aircraftRepository;
    private final IAircraftFileSerializer aircraftFileSerializer;

    public void getAircrafts() throws IOException {
        aircraftRepository.findAll();
    }

    public void saveAircraft(Aircraft aircraft) throws IOException {
        Aircraft serialize = aircraftFileSerializer.serialize(aircraft);
        aircraftRepository.save(serialize);
    }

    public List<Aircraft> getAircraft(int nAircraft) throws IOException {
        return aircraftRepository.findSpecific(nAircraft);
    }

    //It is never used
    public void importData(){

    }
}

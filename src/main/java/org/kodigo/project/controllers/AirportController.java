package org.kodigo.project.controllers;

import lombok.AllArgsConstructor;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.IAircraftFileSerializer;
import org.kodigo.project.persistence.IAircraftRepository;
import org.kodigo.project.persistence.IAirportFileSerializer;
import org.kodigo.project.persistence.IAirportRepository;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class AirportController {
    private final IAirportRepository airportRepository;
    private final IAirportFileSerializer airportFileSerializer;

    public void getAirports() throws IOException {
        airportRepository.findAll();
    }

    public void saveAirport(Airport airport) throws IOException {
        Airport serialize = airportFileSerializer.serialize(airport);
        airportRepository.save(serialize);
    }

    public List<Airport> getAirport(int nAirport) throws IOException {
        return airportRepository.findSpecific(nAirport);
    }

    public Airport getByCity(String city) throws IOException {
        return airportRepository.findByString(city);
    }
}

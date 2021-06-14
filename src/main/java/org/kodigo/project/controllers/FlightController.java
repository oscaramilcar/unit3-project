package org.kodigo.project.controllers;

import lombok.AllArgsConstructor;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.IFlightFileSerializer;
import org.kodigo.project.persistence.IFlightRepository;

import java.io.IOException;

@AllArgsConstructor
public class FlightController {
    private final IFlightRepository flightRepository;
    private final IFlightFileSerializer flightFileSerializer;

    public void getFlights() throws IOException {
        flightRepository.findAll();
    }

    public void saveFlights(Flight flight) throws IOException {
        Flight serialize = flightFileSerializer.serialize(flight);
        flightRepository.save(serialize);
    }

    public void importData(){
        flightRepository.importData();
    }
}
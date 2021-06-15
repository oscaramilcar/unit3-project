package org.kodigo.project.persistence;

import org.kodigo.project.models.Flight;

import java.io.IOException;
import java.util.List;

public interface IFlightRepository {
    void findAll() throws IOException;
    void save(Flight flight) throws IOException;
    void importData() throws IOException;
    List<Flight> findSpecific(int nFlight) throws IOException;
    void updateFlight(Flight flight) throws IOException;
}

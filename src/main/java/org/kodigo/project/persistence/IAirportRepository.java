package org.kodigo.project.persistence;

import org.kodigo.project.models.Airport;

import java.io.IOException;
import java.util.List;

public interface IAirportRepository {
    void findAll() throws IOException;
    void save(Airport airport) throws IOException;
    List<Airport> findSpecific(int nAirport) throws IOException;
    Airport findByString(String city) throws IOException;
}

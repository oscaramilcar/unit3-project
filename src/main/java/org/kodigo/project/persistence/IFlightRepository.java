package org.kodigo.project.persistence;

import org.kodigo.project.models.Flight;

import java.io.IOException;

public interface IFlightRepository {
    void findAll() throws IOException;
    void save(Flight flight) throws IOException;
    void importData() throws IOException;
    Flight find(int number) throws IOException;
}

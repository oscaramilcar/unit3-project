package org.kodigo.project.persistence;

import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;

import java.io.IOException;
import java.util.List;

public interface IAircraftRepository {
    void findAll() throws IOException;
    void save(Aircraft aircraft) throws IOException;
    List<Aircraft> findSpecific(int nAircraft) throws IOException;
}

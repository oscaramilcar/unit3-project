package org.kodigo.project.persistence;

import org.kodigo.project.models.Aircraft;

import java.io.IOException;

public interface IAircraftRepository {
    void findAll() throws IOException;
    void save(Aircraft aircraft) throws IOException;
    void importData();
}

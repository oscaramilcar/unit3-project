package org.kodigo.project.persistence;

import org.kodigo.project.models.Airport;

public interface IAirportFileSerializer {
    Airport serialize(Airport airport);
}

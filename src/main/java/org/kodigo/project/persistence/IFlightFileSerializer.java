package org.kodigo.project.persistence;

import org.kodigo.project.models.Flight;

public interface IFlightFileSerializer {
    Flight serialize(Flight flight);
}

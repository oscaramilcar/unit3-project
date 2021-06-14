package org.kodigo.project.persistence;

import org.kodigo.project.models.Aircraft;

public interface IAircraftFileSerializer {
    Aircraft serialize(Aircraft aircraft);
}

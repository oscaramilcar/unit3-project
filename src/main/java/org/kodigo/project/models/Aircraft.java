package org.kodigo.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aircraft {
    private int nAircraft;
    private String model;
    private int passengerCapacity;
    private int fuelTanksFull;
}

package org.kodigo.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Airport {
    private int noAirport;
    private String airportName;
    private String country;
    private String city;
}

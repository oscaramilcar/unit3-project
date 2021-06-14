package org.kodigo.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {
    private int noFlight;
    private String airline;
    private String typeAircraft;
    private String source;
    private String destination;
    private String date;
    private String departureTime;
    private String arrivalTime;
    //private Aircraft aircraft;
    //private String status;
    //private String comments;
}

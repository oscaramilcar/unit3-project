package org.kodigo.project.persistence;

import org.kodigo.project.models.Flight;

import java.util.Scanner;

public class FlightFilseSerializer implements IFlightFileSerializer{
    @Override
    public Flight serialize(Flight flight){
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ Flight record ************");
        System.out.println("No. Flight: ");
        flight.setNoFlight(Integer.parseInt(scanner.nextLine()));
        System.out.println("Airline: ");
        flight.setAirline(scanner.nextLine());
        System.out.println("Type aircraft: ");
        flight.setTypeAircraft(scanner.nextLine());
        System.out.println("Source: ");
        flight.setSource(scanner.nextLine());
        System.out.println("Destination: ");
        flight.setDestination(scanner.nextLine());
        System.out.println("Date: ");
        flight.setDate(scanner.nextLine());
        System.out.println("Departure time: ");
        flight.setDepartureTime(scanner.nextLine());
        System.out.println("Arrival time");
        flight.setArrivalTime(scanner.nextLine());

        return flight;
    }
}

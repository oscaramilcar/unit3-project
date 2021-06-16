package org.kodigo.project.persistence;

import org.kodigo.project.controllers.AircraftController;
import org.kodigo.project.controllers.AirportController;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;
import org.kodigo.project.models.Flight;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FlightFilseSerializer implements IFlightFileSerializer{
    AirportController airportController = new AirportController(new AirportRepository(), new AirportFileSerializer());
    AircraftController aircraftController = new AircraftController(new AircraftRepository(), new AircraftFileSerializer());
    @Override
    public Flight serialize(Flight flight){
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ Flight record ************");
        System.out.println("Airline: ");
        flight.setAirline(scanner.nextLine());
        //Code that lets choosing a source and a destination
        try{
            //Aircraft
            aircraftController.getAircrafts();
            System.out.println("\nEnter a number of Airport of source: ");
            List<Aircraft> typeAircraft = aircraftController.getAircraft(Integer.parseInt(scanner.nextLine()));
            if (typeAircraft.size() == 1 && typeAircraft.get(0).getNAircraft()
                    != 0){
                //Source
                System.out.println("\nYou chose aircraft model " + typeAircraft.get(0).getModel());
                flight.setTypeAircraft(typeAircraft.get(0).getModel());
            }
            else{
                System.out.println("\nThe flight does not exist");
            }

            //Source
            airportController.getAirports();
            System.out.println("\nEnter a number of Airport of source: ");
            List<Airport> source = airportController.getAirport(Integer.parseInt(scanner.nextLine()));
            //Condition to validate if the flight does exist or not
            if (source.size() == 1 && source.get(0).getNoAirport() != 0){
                //Source
                System.out.print("\nYou chose " + source.get(0).getAirportName() + ", ");
                System.out.println(source.get(0).getCity() + ", " + source.get(0).getCountry() + " as a source\n");
                flight.setSource(source.get(0).getCity());
            }
            else{
                System.out.println("\nThe flight does not exist");
            }

            //Destination
            airportController.getAirports();
            System.out.println("\nEnter a number of Airport of source: ");
            boolean same = true;
            do {
                List<Airport> destination = airportController.getAirport(Integer.parseInt(scanner.nextLine()));
                if (!destination.get(0).getAirportName().equals(source.get(0).getAirportName())){
                    //Condition to validate if the flight does exist or not
                    if (destination.size() == 1 && destination.get(0).getNoAirport() != 0){
                        //Source
                        System.out.print("\nYou chose " + destination.get(0).getAirportName() + ", ");
                        System.out.println(destination.get(0).getCity() + ", " + destination.get(0).getCountry() + " as a destination\n");
                        flight.setDestination(destination.get(0).getCity());
                    }
                    else{
                        System.out.println("\nThe flight does not exist");
                    }
                    same = false;
                }
                else{
                    System.out.println("\nYou cannot choose the same airport");
                    System.out.println("\nEnter a number of Airport of destination: ");
                }
            }while(same);

        }
        catch (IOException ex){
            System.out.println("An error occurred loading the excel file");
        }

        System.out.println("Date: ");
        flight.setDate(scanner.nextLine());
        System.out.println("Departure time: ");
        flight.setDepartureTime(scanner.nextLine());
        System.out.println("Arrival time: ");
        flight.setArrivalTime(scanner.nextLine());
        flight.setStatus("On time");
        flight.setComments("No comments yet");


        return flight;
    }
}

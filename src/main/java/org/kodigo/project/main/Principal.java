package org.kodigo.project.main;

import org.kodigo.project.controllers.AircraftController;
import org.kodigo.project.controllers.AirportController;
import org.kodigo.project.controllers.FlightController;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlightController flightController = new FlightController(new FlightRepository(), new FlightFilseSerializer());
        AircraftController aircraftController = new AircraftController(new AircraftRepository(), new AircraftFileSerializer());
        AirportController airportController = new AirportController(new AirportRepository(), new AirportFileSerializer());

        String menu;
        while (true){
            System.out.println("1) load data");
            System.out.println("2) list flights");
            System.out.println("3) change the status of a flight");
            System.out.println("4) insert a new flight by keyboard");
            System.out.println("5) list aircraft");
            System.out.println("6) insert aircraft by keyboard");
            System.out.println("7) list airports");
            System.out.println("8) insert a new airport");
            System.out.println("exit press (s)");
            String subMenu = scanner.nextLine();
            switch (subMenu){
                case "1":
                    flightController.importData();
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "2":
                    flightController.getFlights();
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "3":
                    flightController.getFlights();
                    System.out.println("\nEnter a number flight: ");
                    List<Flight> data = flightController.getFlight(Integer.parseInt(scanner.nextLine()));
                    //Condition to validate if the flight does exist or not
                    if (data.size() == 1 && data.get(0).getNoFlight() != 0){
                        System.out.println("\nThe current status of the flight number " + data.get(0).getNoFlight());
                        System.out.println("Its current status is: " + data.get(0).getStatus());
                        System.out.println("\nDo you want to modify the flight status? (Y/N)" );
                        char decision;
                        decision = scanner.nextLine().toUpperCase().charAt(0);
                        if (decision == 'Y'){
                            System.out.println("\n1) On time");
                            System.out.println("2) Delayed");
                            System.out.println("3) Cancelled");
                            System.out.println("4) Landed");
                            System.out.println("exit press (s)");
                            menu = scanner.nextLine();
                            switch (menu){
                                case "1":
                                    data.get(0).setStatus("On time");
                                    data.get(0).setComments("No comments yet");
                                    flightController.updateFlight(data.get(0));
                                    break;
                                case "2":
                                    data.get(0).setStatus("Delayed");
                                    System.out.println("\nCurrent date: " + data.get(0).getDate());
                                    System.out.println("\nEnter the new date of the flight: ");
                                    data.get(0).setDate(scanner.nextLine());
                                    System.out.println("\nCurrent departure time: " + data.get(0).getDepartureTime());
                                    System.out.println("\nEnter the new departure time: ");
                                    data.get(0).setDepartureTime(scanner.nextLine());
                                    System.out.println("\nCurrent departure time: " + data.get(0).getArrivalTime());
                                    System.out.println("\nEnter the new arrival time: ");
                                    data.get(0).setArrivalTime(scanner.nextLine());
                                    data.get(0).setComments("No comments yet");
                                    flightController.updateFlight(data.get(0));
                                    break;
                                case "3":
                                    data.get(0).setStatus("Cancelled");
                                    System.out.println("Why the flight was cancelled: ");
                                    data.get(0).setComments(scanner.nextLine());
                                    flightController.updateFlight(data.get(0));
                                    break;
                                case "4":
                                    data.get(0).setStatus("Landed");
                                    System.out.println("Please, enter all the incidents occurred throw the flight: ");
                                    data.get(0).setComments(scanner.nextLine());
                                    flightController.updateFlight(data.get(0));
                                    break;
                                default:
                                    System.out.println("ERROR: Invalid Selection");
                                    System.out.println("press enter to return to the menu");
                                    scanner.nextLine();
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("\nThe flight does not exist");
                    }
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "4":
                    flightController.saveFlights(new Flight());
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "5":
                    aircraftController.getAircrafts();
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "6":
                    aircraftController.saveAircraft(new Aircraft());
                    System.out.println("Success end!!.");
                    scanner.nextLine();
                    break;
                case "7":
                    airportController.getAirports();
                    System.out.println("Success end!!.");
                    scanner.nextLine();
                    break;
                case "8":
                    airportController.saveAirport(new Airport());
                    System.out.println("Success end!!.");
                    scanner.nextLine();
                    break;
                case "s":
                    System.out.println("Success end!!.");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("ERROR: Invalid Selection");
                    System.out.println("press enter to return to the menu");
                    scanner.nextLine();
                    break;
            }
        }
    }
}
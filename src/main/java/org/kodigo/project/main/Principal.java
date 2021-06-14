package org.kodigo.project.main;

import org.kodigo.project.controllers.AircraftController;
import org.kodigo.project.controllers.FlightController;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.AircraftFileSerializer;
import org.kodigo.project.persistence.AircraftRepository;
import org.kodigo.project.persistence.FlightFilseSerializer;
import org.kodigo.project.persistence.FlightRepository;

import java.io.IOException;
import java.util.Scanner;

public class Principal{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlightController flightController = new FlightController(new FlightRepository(), new FlightFilseSerializer());
        AircraftController aircraftController = new AircraftController(new AircraftRepository(), new AircraftFileSerializer());
        String menu;
        while (true){
            System.out.println("1) load data");
            System.out.println("2) list flights");
            System.out.println("3) insert flight by keyboard");
            System.out.println("4) load data");
            System.out.println("5) list flights");
            System.out.println("6) insert flight by keyboard");
            System.out.println("exit press (s)");
            menu = scanner.nextLine();
            switch (menu){
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
                    flightController.saveFlights(new Flight());
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "4":
                    System.out.println("Not implemented");
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "5":
                    aircraftController.getAircraft();
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "6":
                    aircraftController.saveAircraft(new Aircraft());
                    System.out.println("Success end!!.");
                    System.exit(0);
                    break;
                case "s":
                    System.out.println("Success end!!.");
                    System.exit(0);
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
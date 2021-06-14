package org.kodigo.project.main;

import org.kodigo.project.controllers.FlightController;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.FlightFilseSerializer;
import org.kodigo.project.persistence.FlightRepository;

import java.io.IOException;
import java.util.Scanner;

public class Principal{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlightController flightController = new FlightController(new FlightRepository(), new FlightFilseSerializer());
        String menu;
        while (true){
            System.out.println("1) load data");
            System.out.println("2) list flights");
            System.out.println("3) insert flight by keyboard");
            System.out.println("exit press (s)");
            menu = scanner.nextLine();
            switch (menu){
                case "1":
                    System.out.println("Not implemented");
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
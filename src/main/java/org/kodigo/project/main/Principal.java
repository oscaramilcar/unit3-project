package org.kodigo.project.main;

import org.kodigo.project.Documents.FlightReport;
import org.kodigo.project.controllers.AircraftController;
import org.kodigo.project.controllers.FlightController;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Flight;
import org.kodigo.project.notifications.EmailSender;
import org.kodigo.project.notifications.FlightNotifierProcessor;
import org.kodigo.project.persistence.*;

import java.io.IOException;
import java.util.Scanner;

public class Principal{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlightController flightController = new FlightController(new FlightRepository(), new FlightFilseSerializer());
        AircraftController aircraftController = new AircraftController(new AircraftRepository(), new AircraftFileSerializer());
        FlightNotifierProcessor flightNotifierProcessor = new FlightNotifierProcessor(new FlightRepository(),new FlightReport(),new EmailSender());
        String menu;
        int numFliht;
        while (true){
            System.out.println("1) load data");
            System.out.println("2) list flights");
            System.out.println("3) insert flight by keyboard");
            System.out.println("4) list aircraft");
            System.out.println("5) insert flight by keyboard");
            System.out.println("6) send report by email");
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
                    aircraftController.getAircraft();
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "5":
                    aircraftController.saveAircraft(new Aircraft());
                    System.out.println("\npress enter to return to the menu");
                    scanner.nextLine();
                    break;
                case "6":
                    flightController.getFlights();
                    System.out.println("Enter flight number: ");
                    numFliht = Integer.parseInt(scanner.nextLine());
                    flightNotifierProcessor.sendReportsByFlightProcessor(numFliht);
                    //Flight flightSelected = flightController.getFlight(numFliht);
                    //FlightReport report = new FlightReport(flightSelected);
                    //report.toExcel();
                    //report.toPdf();
                    //emailSender.notify("FlightReport.pdf","FlightReport.xlsx");
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
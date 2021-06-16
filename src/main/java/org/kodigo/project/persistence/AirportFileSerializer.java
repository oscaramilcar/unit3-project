package org.kodigo.project.persistence;

import org.kodigo.project.models.Airport;

import java.util.Scanner;

public class AirportFileSerializer implements IAirportFileSerializer{
    @Override
    public Airport serialize(Airport airport){
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ Airport record ************");
        System.out.println("Airport name: ");
        airport.setAirportName(scanner.nextLine());
        System.out.println("Country: ");
        airport.setCountry(scanner.nextLine());
        System.out.println("City: ");
        airport.setCity(scanner.nextLine());

        return airport;
    }
}

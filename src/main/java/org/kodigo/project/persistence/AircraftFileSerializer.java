package org.kodigo.project.persistence;

import org.kodigo.project.models.Aircraft;

import java.util.Scanner;

public class AircraftFileSerializer implements IAircraftFileSerializer{
    @Override
    public Aircraft serialize(Aircraft aircraft){
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ Aircraft record ************");
        System.out.println("Model: ");
        aircraft.setModel(scanner.nextLine());
        System.out.println("Passenger capacity: ");
        aircraft.setPassengerCapacity(Integer.parseInt(scanner.nextLine()));
        System.out.println("Capacity with full tank: ");
        aircraft.setFuelTanksFull(Integer.parseInt(scanner.nextLine()));

        return aircraft;
    }
}

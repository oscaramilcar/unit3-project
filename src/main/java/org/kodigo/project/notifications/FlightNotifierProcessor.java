package org.kodigo.project.notifications;

import lombok.AllArgsConstructor;
import org.kodigo.project.Documents.FlightReport;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.IFlightRepository;

import java.io.IOException;

@AllArgsConstructor
public class FlightNotifierProcessor {
    private final IFlightRepository flightRepository;
    private final FlightReport flightReport;
    private final IFlightNotifier flightNotifier;

    public void sendReportsByFlightProcessor(int numFlight) throws IOException {
        Flight flightSelected = flightRepository.find(numFlight);
        flightReport.setFlight(flightSelected);
        flightReport.toPdf();
        flightReport.toExcel();
        flightNotifier.notify("FlightReport.pdf","FlightReport.xlsx");
    }
}

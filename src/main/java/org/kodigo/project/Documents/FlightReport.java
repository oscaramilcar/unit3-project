package org.kodigo.project.Documents;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kodigo.project.ApiPublic.WeatherState;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Flight;
import org.kodigo.project.persistence.IAircraftRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FlightReport implements IExportablePdf, IExportableExcel {
    @Getter
    @Setter
    private List<Flight> flight;
    private final IAircraftRepository aircraftRepository;
    private final WeatherState weatherState;
    public FlightReport(IAircraftRepository aircraftRepository, WeatherState weatherState){
        this.aircraftRepository = aircraftRepository;
        this.weatherState = weatherState;
    }

    @Override
    public void toExcel() {
        try {
            XSSFWorkbook book = new XSSFWorkbook();
            XSSFSheet sheet1 = book.createSheet("flight");
            List<Flight> flightData = new ArrayList<>();
            flightData.add(getFlight().get(0));


            //Heading of the document
            String[] header = {"No FLIGHT",	"AIRLINE", "TYPE AIRCRAFT","CAPACITY PASSENGERS","FUEL TANKS" ,"SOURCE", "DESTINATION", "DATE","DEPARTURE TIME", "ARRIVAL TIME","WEATHER CONDITION"};

            //Adding style to the excel sheet
            CellStyle style = book.createCellStyle();
            Font font = book.createFont();
            font.setBold(true);
            style.setFont(font);


            //Generating data for the document
            //Header
            XSSFRow row = sheet1.createRow(0);
            for (int i = 0; i < header.length; i++){
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(header[i]);
            }

            //content
            int count = 0;

            for (Flight data: flightData){
                row = sheet1.createRow(++count);
                Aircraft aircfraft = aircraftRepository.findByString(data.getTypeAircraft());

                XSSFCell cell = row.createCell(0);
                cell.setCellValue(data.getNoFlight());

                cell = row.createCell(1);
                cell.setCellValue(data.getAirline());

                cell = row.createCell(2);
                cell.setCellValue(data.getTypeAircraft());

                cell = row.createCell(3);
                cell.setCellValue( aircfraft.getPassengerCapacity());

                cell = row.createCell(4);
                cell.setCellValue(aircfraft.getFuelTanksFull());

                cell = row.createCell(5);
                cell.setCellValue(data.getSource());

                cell = row.createCell(6);
                cell.setCellValue(data.getDestination());

                cell = row.createCell(7);
                cell.setCellValue(data.getDate());

                cell = row.createCell(8);
                cell.setCellValue(data.getDepartureTime());

                cell = row.createCell(9);
                cell.setCellValue(data.getArrivalTime());

                cell = row.createCell(10);
                cell.setCellValue(weatherState.GetweatherStatus(data.getDestination()));

                cell = row.createCell(11);
                cell.setCellValue(data.getComments());
            }

            try{
                OutputStream fileOut = new FileOutputStream("FlightReport.xlsx");
                System.out.println("The excel file has been created successfully...\n");
                book.write(fileOut);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toPdf() {
        String[] header = {"No FLIGHT",	"AIRLINE", "SOURCE", "DESTINATION", "DATE","DEPARTURE TIME", "ARRIVAL TIME"};
        //Create the document
        Document document = new Document(PageSize.A3.rotate());
        List<Flight> flightData = new ArrayList<>();
        flightData.add(getFlight().get(0));
        String city = "london";
        String comments = "";
        Aircraft aircraft = new Aircraft();
        try {
            FileOutputStream pdfFile = new FileOutputStream("FlightReport.pdf");

            //Linking the file and document
            PdfWriter.getInstance(document, pdfFile);

            //Open document
            document.open();

            //Paragraph
            Paragraph title = new Paragraph("List of Scheduled Flights\n\n",
                    FontFactory.getFont("arial",
                            22,
                            BaseColor.BLUE));

            document.add(title);

            //Creating a table for showing data
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.getDefaultCell().setUseAscender(true);
            table.getDefaultCell().setUseDescender(true);


            //Add table header
            for (String s : header) {
                table.addCell(s);
            }

            //Add the content
            for (Flight data: flightData){
                city = data.getDestination();
                comments = data.getComments();
                aircraft = aircraftRepository.findByString(data.getTypeAircraft());
                table.addCell(Integer.toString(data.getNoFlight()));
                table.addCell(data.getAirline());
                table.addCell(data.getSource());
                table.addCell(data.getDestination());
                table.addCell(data.getDate());
                table.addCell(data.getDepartureTime());
                table.addCell(data.getArrivalTime());
            }

            //Add the table to the document
            document.add(table);

            StringBuilder aircraftInfo = new StringBuilder();
            Paragraph subtitleWeather = new Paragraph(String.format("\n\n%s\n", "WEATHER CONDITION"), FontFactory.getFont("arial",12,BaseColor.DARK_GRAY));
            document.add(subtitleWeather);

            Paragraph weatherInfo = new Paragraph(String.format("%s\n", weatherState.GetweatherStatus(city)), FontFactory.getFont("arial",12,BaseColor.DARK_GRAY));
            document.add(weatherInfo);

            Paragraph aircraftSubtitle = new Paragraph(String.format("\n\n%s\n", "AIRCRAFT INFO"), FontFactory.getFont("arial",10,BaseColor.DARK_GRAY));
            document.add(aircraftSubtitle);

            aircraftInfo.append("Type aircraft: ")
                    .append(aircraft.getModel())
                    .append("\n")
                    .append("CAPACITY PASSENGERS: ")
                    .append(aircraft.getPassengerCapacity())
                    .append("\n")
                    .append("FUEL TANKS: ")
                    .append(aircraft.getFuelTanksFull());

            Paragraph aircraftResult = new Paragraph(String.format("%s\n",aircraftInfo), FontFactory.getFont("arial",10,BaseColor.DARK_GRAY));
            document.add(aircraftResult);

            Paragraph commentsSubtitle = new Paragraph(String.format("\n\n%s\n", "COMMENTS"), FontFactory.getFont("arial",10,BaseColor.DARK_GRAY));
            document.add(commentsSubtitle);

            Paragraph infoComments = new Paragraph(String.format("\n%s",comments), FontFactory.getFont("arial",10,BaseColor.DARK_GRAY));
            document.add(infoComments);


            //Close document
            document.close();
            System.out.println("The PDF file was created correctly...\n");

        }catch (FileNotFoundException | DocumentException ex){
            System.out.println("An error has occurred");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "FlightReport.pdf";
    }
}

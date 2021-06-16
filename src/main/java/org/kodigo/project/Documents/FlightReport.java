package org.kodigo.project.Documents;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kodigo.project.models.Flight;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FlightReport implements IExportablePdf, IExportableExcel {
    @Getter
    @Setter
    private Flight flight;
    @Override
    public void toExcel() {
        try {
            XSSFWorkbook book = new XSSFWorkbook();
            XSSFSheet sheet1 = book.createSheet("flight");
            List<Flight> flightData = new ArrayList<>();
            flightData.add(getFlight());

            //Heading of the document
            String[] header = {"No FLIGHT",	"AIRLINE", "TYPE AIRCRAFT", "SOURCE", "DESTINATION", "DATE","DEPARTURE TIME", "ARRIVAL TIME"};

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

                XSSFCell cell = row.createCell(0);
                cell.setCellValue(data.getNoFlight());

                cell = row.createCell(1);
                cell.setCellValue(data.getAirline());

                cell = row.createCell(2);
                cell.setCellValue(data.getTypeAircraft());

                cell = row.createCell(3);
                cell.setCellValue(data.getSource());

                cell = row.createCell(4);
                cell.setCellValue(data.getDestination());

                cell = row.createCell(5);
                cell.setCellValue(data.getDate());

                cell = row.createCell(6);
                cell.setCellValue(data.getDepartureTime());

                cell = row.createCell(7);
                cell.setCellValue(data.getArrivalTime());
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
        String[] header = {"No FLIGHT",	"AIRLINE", "TYPE AIRCRAFT", "SOURCE", "DESTINATION", "DATE","DEPARTURE TIME", "ARRIVAL TIME"};
        //Create the document
        Document document = new Document(PageSize.A3.rotate());
        List<Flight> flightData = new ArrayList<>();
        flightData.add(getFlight());
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
                table.addCell(Integer.toString(data.getNoFlight()));
                table.addCell(data.getAirline());
                table.addCell(data.getTypeAircraft());
                table.addCell(data.getSource());
                table.addCell(data.getDestination());
                table.addCell(data.getDate());
                table.addCell(data.getDepartureTime());
                table.addCell(data.getArrivalTime());
            }

            //Add the table to the document
            document.add(table);

            //Close document
            document.close();
            System.out.println("The PDF file was created correctly...\n");

        }catch (FileNotFoundException | DocumentException ex){
            System.out.println("An error has occurred");
        }
        return "FlightReport.pdf";
    }
}

package org.kodigo.project.persistence;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kodigo.project.models.Flight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class FlightRepository implements IFlightRepository {

    @Override
    public void findAll() throws IOException {
        String path = "Flights.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            //System.out.println(sh.getSheetName());
            Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
            while (RowIterator.hasNext()) {
                Row row = RowIterator.next(); //Current row
                Iterator<Cell> cellIterator = row.iterator(); //cells in row
                System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+");
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    System.out.format("| %-17s", cellValue);
                }
                System.out.println("|");
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+");
            workbook.close();
        }
    }

    @Override
    public void save(Flight flight) throws IOException {
        String path = "data/Flights.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        int lastRow = sheet.getLastRowNum();
        int rowNum = ++lastRow;
        for (var f : flights){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(f.getNoFlight());
            row.createCell(1).setCellValue(f.getAirline());
            row.createCell(2).setCellValue(f.getTypeAircraft());
            row.createCell(3).setCellValue(f.getSource());
            row.createCell(4).setCellValue(f.getDestination());
            row.createCell(5).setCellValue(f.getDate());
            row.createCell(6).setCellValue(f.getDepartureTime());
            row.createCell(7).setCellValue(f.getArrivalTime());
        }

        FileOutputStream fo= new FileOutputStream("Flights.xlsx");
        workbook.write(fo);
        fo.close();
        workbook.close();
        System.out.println("Complete!!");
    }

    @Override
    public void importData() {

    }
}

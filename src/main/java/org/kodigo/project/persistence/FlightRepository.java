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
                System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                var count = 0;
                while (cellIterator.hasNext()) {
                    //This function of this logic is to avoid printing the comments information
                    //9 because this is the index of the comments information
                    if (count != 9){
                        Cell cell = cellIterator.next(); //Current cell
                        String cellValue = formatter.formatCellValue(cell);
                        System.out.format("| %-17s", cellValue);
                        count++;
                    }
                    else{
                        Cell cell = cellIterator.next(); //Current cell
                        count = 0;
                    }
                }
                System.out.println("|");
            }
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            workbook.close();
        }
    }

    @Override
    public void save(Flight flight) throws IOException {
        String path = "data/Flights.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        int last = sheet.getLastRowNum();
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        int lastRow = sheet.getLastRowNum();
        int rowNum = ++lastRow;
        for (var f : flights){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(++last);
            row.createCell(1).setCellValue(f.getAirline());
            row.createCell(2).setCellValue(f.getTypeAircraft());
            row.createCell(3).setCellValue(f.getSource());
            row.createCell(4).setCellValue(f.getDestination());
            row.createCell(5).setCellValue(f.getDate());
            row.createCell(6).setCellValue(f.getDepartureTime());
            row.createCell(7).setCellValue(f.getArrivalTime());
            row.createCell(8).setCellValue(f.getStatus());
            row.createCell(9).setCellValue(f.getComments());
        }

        FileOutputStream fo= new FileOutputStream("Flights.xlsx");
        workbook.write(fo);
        fo.close();
        workbook.close();
        System.out.println("Complete!!");
    }

    @Override
    public void importData() throws IOException{
        //Where the data is going to be extracted
        String importPath = "importFlights.xlsx";
        FileInputStream importFile = new FileInputStream(importPath);
        Workbook importWorkbook = new XSSFWorkbook(importFile); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = importWorkbook.sheetIterator();
        List<String> data = new ArrayList<>();
        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
            while (RowIterator.hasNext()) {
                Row row = RowIterator.next(); //Current row
                Iterator<Cell> cellIterator = row.iterator(); //cells in row
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    data.add(cellValue);
                }
            }
            importWorkbook.close();
        }

        //the data is going to ve saved
        String path = "data/Flights.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        int rowNum = lastRow;
        int count = 0;
        Row row = null;
        //It starts on 8 because the first 8 values are the header
        for (int i = 10; i < data.size(); i++){ //I used this way to loop the list because i used a List<String> not save object of Flight
            //This is in order to set it into as a integer value
            //This for create a new row
            if (count == 0){
                row = sheet.createRow(++rowNum);
                row.createCell(count).setCellValue(++lastRow);
            }
            else {
                row.createCell(count).setCellValue(data.get(i));
            }
            count++;

            //if statement for reset the value in create a new row
            if (count > 9){
                count = 0;
            }
        }

        FileOutputStream fo= new FileOutputStream("Flights.xlsx");
        workbook.write(fo);
        fo.close();
        workbook.close();
        System.out.println("Complete!!");
    }

    @Override
    public List<Flight> findSpecific(int nFlight) throws IOException {
        String path = "Flights.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight());
        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            //System.out.println(sh.getSheetName());
            Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
            while (RowIterator.hasNext()) {
                Row row = RowIterator.next(); //Current row
                Iterator<Cell> cellIterator = row.iterator(); //cells in row
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    if (Integer.toString(nFlight).equals(cellValue)){

                        flightList.get(0).setNoFlight(Integer.parseInt(cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setAirline((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setTypeAircraft((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setSource((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setDestination((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setDate((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setDepartureTime((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setArrivalTime((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setStatus((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        flightList.get(0).setComments((cellValue));
                    }
                }
            }
            workbook.close();
        }
        return flightList;
    }

    @Override
    public void updateFlight(Flight flight) throws IOException {
        String path = "data/Flights.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight());
        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            //System.out.println(sh.getSheetName());
            Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
            while (RowIterator.hasNext()) {
                Row row = RowIterator.next(); //Current row
                Iterator<Cell> cellIterator = row.iterator(); //cells in row
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    if (Integer.toString(flight.getNoFlight()).equals(cellValue)){

                            row.createCell(0).setCellValue(flight.getNoFlight());
                            row.createCell(1).setCellValue(flight.getAirline());
                            row.createCell(2).setCellValue(flight.getTypeAircraft());
                            row.createCell(3).setCellValue(flight.getSource());
                            row.createCell(4).setCellValue(flight.getDestination());
                            row.createCell(5).setCellValue(flight.getDate());
                            row.createCell(6).setCellValue(flight.getDepartureTime());
                            row.createCell(7).setCellValue(flight.getArrivalTime());
                            row.createCell(8).setCellValue(flight.getStatus());
                            row.createCell(9).setCellValue(flight.getComments());
                    }
                }
            }
            FileOutputStream fo= new FileOutputStream("Flights.xlsx");
            workbook.write(fo);
            fo= new FileOutputStream("data/Flights.xlsx");
            workbook.write(fo);
            fo.close();
            workbook.close();
            System.out.println("Complete!!");
        }
    }
}

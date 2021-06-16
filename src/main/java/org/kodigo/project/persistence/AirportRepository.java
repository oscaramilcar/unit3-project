package org.kodigo.project.persistence;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kodigo.project.models.Aircraft;
import org.kodigo.project.models.Airport;
import org.kodigo.project.models.Flight;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class AirportRepository implements IAirportRepository {

    @Override
    public void findAll() throws IOException {
        String path = "Airports.xlsx";
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
                System.out.println("+---------------------------------------------------------------------------------------------------------------------+");
                var count = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    if (count == 1){
                        System.out.format("| %-44s", cellValue);
                    }else{
                        System.out.format("| %-22s", cellValue);
                    }
                    if (count == 3) {
                        count = 0;
                    } else {
                        count++;
                    }
                }
                System.out.println("|");
            }
            System.out.println("+---------------------------------------------------------------------------------------------------------------------+");
            workbook.close();
        }
    }

    @Override
    public void save(Airport airport) throws IOException {
        String path = "data/Airports.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        int lastRow = sheet.getLastRowNum();
        for (var a : airports){
            Row row = sheet.createRow(++lastRow);
            row.createCell(0).setCellValue(lastRow);
            row.createCell(1).setCellValue(a.getAirportName());
            row.createCell(2).setCellValue(a.getCountry());
            row.createCell(3).setCellValue(a.getCity());
        }

        FileOutputStream fo= new FileOutputStream("Airports.xlsx");
        workbook.write(fo);
        fo.close();
        workbook.close();
        System.out.println("Complete!!");
    }

    @Override
    public List<Airport> findSpecific(int nAirport) throws IOException {
        String path = "data/Airports.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport());
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
                    if (Integer.toString(nAirport).equals(cellValue)){

                        airportList.get(0).setNoAirport(Integer.parseInt(cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        airportList.get(0).setAirportName((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        airportList.get(0).setCountry((cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        airportList.get(0).setCity((cellValue));
                    }
                }
            }
            workbook.close();
        }
        return airportList;
    }

    public Airport findByString(String city) throws IOException {
        String path = "data/Airports.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        List<Airport> airportList = new ArrayList<>();
        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            //System.out.println(sh.getSheetName());
            Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
            RowIterator.next();
            while (RowIterator.hasNext()) {
                Row row = RowIterator.next(); //Current row
                Iterator<Cell> cellIterator = row.iterator(); //cells in row
                int count = 0;
                while (cellIterator.hasNext()) {
                    Airport newAirport = new Airport();

                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    newAirport.setNoAirport(Integer.parseInt(cellValue));

                    cell = cellIterator.next(); //Current cell
                    cellValue = formatter.formatCellValue(cell);
                    newAirport.setAirportName(cellValue);

                    cell = cellIterator.next(); //Current cell
                    cellValue = formatter.formatCellValue(cell);
                    newAirport.setCountry(cellValue);

                    cell = cellIterator.next(); //Current cell
                    cellValue = formatter.formatCellValue(cell);
                    newAirport.setCity(cellValue);

                    airportList.add(newAirport);
                }
            }
            workbook.close();
        }

        System.out.println(airportList.size());
        for (Airport a: airportList){
            if (city.equals(a.getCity())){
                return a;
            }
        }
        return new Airport();
    }
}

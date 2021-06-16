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
public class AircraftRepository implements IAircraftRepository {

    @Override
    public void findAll() throws IOException {
        String path = "Aircrafts.xlsx";
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
                System.out.println("+-----------------------------------------------------------------------------------------------+");
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Current cell
                    String cellValue = formatter.formatCellValue(cell);
                    System.out.format("| %-22s", cellValue);
                }
                System.out.println("|");
            }
            System.out.println("+-----------------------------------------------------------------------------------------------+");
            workbook.close();
        }
    }

    @Override
    public void save(Aircraft aircraft) throws IOException {
        String path = "data/Aircrafts.xlsx";
        Workbook workbook = new XSSFWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(aircraft);

        int lastRow = sheet.getLastRowNum();
        for (var a : aircrafts){
            Row row = sheet.createRow(lastRow);

            row.createCell(0).setCellValue(++lastRow);
            row.createCell(1).setCellValue(a.getModel());
            row.createCell(2).setCellValue(a.getPassengerCapacity());
            row.createCell(3).setCellValue(a.getFuelTanksFull());
        }

        FileOutputStream fo= new FileOutputStream("Aircrafts.xlsx");
        workbook.write(fo);
        fo.close();
        workbook.close();
        System.out.println("Complete!!");
    }

    public List<Aircraft> findSpecific(int nAircraft) throws IOException {
        String path = "Aircrafts.xlsx";
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file); //create new workbook
        DataFormatter formatter = new DataFormatter(); //Formatter values cells
        Iterator<Sheet> sheets = workbook.sheetIterator();
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(new Aircraft());
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
                    if (Integer.toString(nAircraft).equals(cellValue)){

                        aircraftList.get(0).setNAircraft(Integer.parseInt(cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        aircraftList.get(0).setModel(cellValue);
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        aircraftList.get(0).setPassengerCapacity(Integer.parseInt(cellValue));
                        cell = cellIterator.next(); //Current cell
                        cellValue = formatter.formatCellValue(cell);

                        aircraftList.get(0).setFuelTanksFull(Integer.parseInt(cellValue));
                    }
                }
            }
            workbook.close();
        }
        return aircraftList;
    }
}

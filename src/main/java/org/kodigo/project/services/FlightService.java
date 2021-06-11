package org.kodigo.project.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class FlightService implements IFlightService{
    @Override
    public void getFlights() {
        try {
            FileInputStream file = new FileInputStream(new File("Data.xlsx"));
            Workbook workbook = new XSSFWorkbook(file); //create new workbook
            DataFormatter formatter = new DataFormatter(); //Formatter values cells
            Iterator<Sheet> sheets = workbook.sheetIterator();
            while (sheets.hasNext()){
                Sheet sh = sheets.next();
                //System.out.println(sh.getSheetName());
                Iterator<Row> RowIterator = sh.iterator(); // Iterate the rows on the sheet
                while (RowIterator.hasNext()){
                    Row row = RowIterator.next(); //Current row
                    Iterator<Cell> cellIterator = row.iterator(); //cells in row
                    System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+");
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next(); //Current cell
                        String cellValue = formatter.formatCellValue(cell);
                        System.out.format("| %-17s",cellValue);
                    }
                    System.out.println("|");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+");
            workbook.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

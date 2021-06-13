package com.kodigo.airport.utils;

import com.kodigo.airport.dto.FlightDTO;
import com.kodigo.airport.service.FlightService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

public class ExcelBatch implements IFileRead{
    @Autowired
    private FlightService flightXlsxService;
    public void read() {

        FlightDTO flight;
        String path = "./batch.xlsx";

        try {
            File file = new File(path);
            XSSFWorkbook excel;
            XSSFSheet sheet;

            if(!file.exists()){

                excel = new XSSFWorkbook();
                sheet = excel.createSheet("sheet 1");
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("Number flight");
                row.createCell(1).setCellValue("Airline");
                row.createCell(2).setCellValue("Airplane model");
                row.createCell(3).setCellValue("Departure city");
                row.createCell(4).setCellValue("Arrival city");
                row.createCell(5).setCellValue("Departure date and time");
                row.createCell(6).setCellValue("Arrival date and time");
                excel.write(new FileOutputStream(path));

            }

            excel = new XSSFWorkbook(new FileInputStream(file));
            sheet = excel.getSheetAt(0);
            int numRows = sheet.getLastRowNum();

            for(int i = 0; i <= numRows; i++){
                if(sheet.getRow(i) != null){
                    Row row = sheet.getRow(i);
                    flight = new FlightDTO();
                    flight.setIdFlight(Integer.parseInt(row.getCell(0).toString()));
                    flight.setIdAirline(Integer.parseInt(row.getCell(1).toString()));
                    flight.setModel(row.getCell(2).toString());
                    flight.setIdDepartureCity(Integer.parseInt(row.getCell(3).toString()));
                    flight.setIdArrivalCity(Integer.parseInt(row.getCell(4).toString()));
                    flight.setDepartureTime(Date.valueOf(row.getCell(5).toString()));
                    flight.setArrivalTime(Date.valueOf(row.getCell(6).toString()));
                    //this.flightXlsxService.create(flight);
                }
            }

        }catch (IOException e) {
            System.out.println("An error occurred: "+e.getMessage());
        }
    }
}


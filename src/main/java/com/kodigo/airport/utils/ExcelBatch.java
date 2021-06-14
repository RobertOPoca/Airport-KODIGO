package com.kodigo.airport.utils;

import com.kodigo.airport.model.Flight;
import com.kodigo.airport.model.City;
import com.kodigo.airport.model.Airline;
import com.kodigo.airport.model.Airplane;
import com.kodigo.airport.service.AirlineService;
import com.kodigo.airport.service.AirplaneService;
import com.kodigo.airport.service.CityService;
import com.kodigo.airport.service.FlightService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class ExcelBatch implements IFileRead{
    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AirlineService airlineService;

    public boolean read() {
        boolean success = false;

        try {
            Flight flight;
            City cityDeparture;
            City cityArrival;
            Airline airline;
            Airplane airplane;
            String path = "./batch2.xlsx";
            File file = new File(path);
            XSSFWorkbook excel;
            XSSFSheet sheet;

            if(!file.exists()){

                excel = new XSSFWorkbook();
                sheet = excel.createSheet("sheet 1");
                Row row = sheet.createRow(0);
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

            for(int i = 1; i <= numRows; i++){
                if(sheet.getRow(i) != null){

                    Row row = sheet.getRow(i);
                    flight = new Flight();

                    airline= this.airlineService.findById((int) Math.round(row.getCell(1).getNumericCellValue()));
                    flight.setAirline(airline);

                    airplane = this.airplaneService.findById(row.getCell(2).toString());
                    flight.setAirplane(airplane);

                    cityDeparture = this.cityService.findById((int) Math.round(row.getCell(3).getNumericCellValue()));
                    flight.setDepartureCity(cityDeparture);

                    cityArrival = this.cityService.findById((int) Math.round(row.getCell(4).getNumericCellValue()));
                    flight.setArrivalCity(cityArrival);

                    flight.setDepartureTime(new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(row.getCell(5).getDateCellValue()));
                    flight.setArrivalTime(new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(row.getCell(6).getDateCellValue()));
                    flight.setStatus("ONTIME");

                    this.flightService.create(flight);
                }
            }
            success = true;

        }catch (IOException e) {
            System.out.println("An error occurred: "+e.getMessage());
        }
        return success;
    }
}


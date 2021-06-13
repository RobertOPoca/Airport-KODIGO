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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

public class ExcelBatch implements IFileRead{
    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AirlineService airlineService;

    public String read() {
        Flight flight;
        City cityDeparture;
        City cityArrival;
        Airline airline;
        Airplane airplane;
        String path = "./batch.xlsx";
        String message = "";

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
                    flight = new Flight();
                    flight.setIdFlight(Integer.parseInt(row.getCell(0).toString()));

                    airline= this.airlineService.findById(Integer.parseInt(row.getCell(1).toString()));
                    flight.setAirline(airline);

                    airplane = this.airplaneService.findById(row.getCell(2).toString());
                    flight.setAirplane(airplane);

                    cityDeparture = this.cityService.findById(Integer.parseInt(row.getCell(3).toString()));
                    flight.setDepartureCity(cityDeparture);

                    cityArrival = this.cityService.findById(Integer.parseInt(row.getCell(4).toString()));
                    flight.setArrivalCity(cityArrival);

                    flight.setDepartureTime(Date.valueOf(row.getCell(5).toString()));
                    flight.setArrivalTime(Date.valueOf(row.getCell(6).toString()));
                    flight.setStatus("ONTIME");
                    //flight.setDepartureTime(new java.util.Date());
                    //flight.setArrivalTime(new java.util.Date());

                    this.flightService.create(flight);
                    message = "flights was created successfully";;
                }
            }

        }catch (IOException e) {
            message = "An error occurred: "+e.getMessage();
        }
        return message;
    }
}


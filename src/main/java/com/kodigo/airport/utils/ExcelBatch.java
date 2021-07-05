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

import java.io.*;
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

    private Airline fillAirline(int id){
        return this.airlineService.findById(id);
    }

    private Airplane fillAirplane(String id){
        return this.airplaneService.findById(id);
    }

    private City fillCity(int id){
        return this.cityService.findById(id);
    }

    private Flight fillFlight(Row row){

        Flight flight = new Flight();
        flight.setAirline( fillAirline( (int) Math.round( row.getCell(1).getNumericCellValue() )));
        flight.setAirplane(fillAirplane(row.getCell(2).toString()));
        flight.setDepartureCity(fillCity((int) Math.round(row.getCell(3).getNumericCellValue())));
        flight.setArrivalCity(fillCity((int) Math.round(row.getCell(4).getNumericCellValue())));
        flight.setDepartureTime(new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(row.getCell(5).getDateCellValue()));
        flight.setArrivalTime(new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(row.getCell(6).getDateCellValue()));
        flight.setStatus("ONTIME");

        return flight;
    }

    private void cellsToParameter(XSSFSheet sheet){
        for(int i = 1; i <= sheet.getLastRowNum(); i++){
            if(sheet.getRow(i) != null){
                Row row = sheet.getRow(i);
                this.flightService.create(fillFlight(row));
            }
        }
    }

    private boolean saveInDataBase(String path){

        try ( FileInputStream file = new FileInputStream(path);
              XSSFWorkbook excel = new XSSFWorkbook(file) ){

            XSSFSheet sheet = excel.getSheetAt(0);
            cellsToParameter(sheet);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean read() {

        String path = "./batch2.xlsx";
        File file = new File(path);

        if(file.exists()){
            return saveInDataBase(path);
        }else{
            return false;
        }
    }
}
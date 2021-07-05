package com.kodigo.airport.utils;

import com.kodigo.airport.model.Incident;
import com.kodigo.airport.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

@AllArgsConstructor
public class ExcelReport implements IFileWrite{

    private String weather;
    private List<Flight> flightList;
    private List<Incident> incidentList;

    private void makeHeader(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Number flight");
        row.createCell(1).setCellValue("Airline");
        row.createCell(2).setCellValue("Airplane model");
        row.createCell(3).setCellValue("Departure city");
        row.createCell(4).setCellValue("Arrival city");
        row.createCell(5).setCellValue("Departure date and time");
        row.createCell(6).setCellValue("Arrival date and time");
        row.createCell(7).setCellValue("Status");
        row.createCell(8).setCellValue("Weather");
        row.createCell(9).setCellValue("Incidents");
    }

    private void deleteRows(XSSFSheet sheet){
        for(int i = sheet.getLastRowNum() ; i > 0; i--){
            sheet.removeRow(sheet.getRow(i));
        }
    }

    private void makeIncidentsCells(Row row, int idFlight){

        int numCol = 8;

        for(Incident incident: incidentList){
            if(incident.getFlight().getIdFlight().equals(idFlight)){
                row.createCell(numCol++).setCellValue(incident.getDescription() + ". "+incident.getDateTime());
            }
        }
    }

    private String getDeparturePlace(Flight flight){
        return flight.getDepartureCity().getCityName() +", "+ flight.getDepartureCity().getCountry().getCountryName();
    }

    private String getArrivalPlace(Flight flight){
        return flight.getArrivalCity().getCityName() +", "+ flight.getArrivalCity().getCountry().getCountryName();
    }

    private void makeRows(Row row, Flight flight){

        row.createCell(0).setCellValue(flight.getIdFlight().toString());
        row.createCell(1).setCellValue(flight.getAirline().getAirlineName());
        row.createCell(2).setCellValue(flight.getAirplane().getModel());
        row.createCell(3).setCellValue(getDeparturePlace(flight));
        row.createCell(4).setCellValue(getArrivalPlace(flight));
        row.createCell(5).setCellValue(flight.getDepartureTime());
        row.createCell(6).setCellValue(flight.getArrivalTime());
        row.createCell(7).setCellValue(flight.getStatus());
        row.createCell(8).setCellValue(weather);
    }

    private void parametersToCells(XSSFSheet sheet){

        int numRow = 0;
        for (Flight flight : flightList) {
            Row row = sheet.createRow(numRow++);
            makeRows(row, flight);
            makeIncidentsCells(row, flight.getIdFlight());
        }
    }

    private boolean writeFileIsExist(String path){

        try ( FileInputStream file = new FileInputStream(path);
              XSSFWorkbook excel = new XSSFWorkbook(file) ){

            XSSFSheet sheet = excel.getSheetAt(0);
            deleteRows(sheet);
            parametersToCells(sheet);
            excel.write(new FileOutputStream(path));
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean writeFileIsNotExist(String path){

        try (XSSFWorkbook excel = new XSSFWorkbook() ){

            XSSFSheet sheet = excel.getSheetAt(0);
            makeHeader(sheet);
            parametersToCells(sheet);
            excel.write(new FileOutputStream(path));
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean write(){

        String path = "./report.xlsx";
        File file = new File(path);

        if(file.exists()){
            return writeFileIsExist(path);
        }else{
            return writeFileIsNotExist(path);
        }
    }
}
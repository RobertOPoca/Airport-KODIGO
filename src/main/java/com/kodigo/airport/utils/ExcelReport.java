package com.kodigo.airport.utils;
<<<<<<< HEAD

import com.kodigo.airport.model.Incident;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.repository.IFlightRepository;
import com.kodigo.airport.repository.IIncidentRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelReport implements IFileWrite{

    @Override
    public boolean write(List<Incident> incidentList, List<Flight> flightList,String weather) {
        boolean success = false;
        try{
            String path = "./report.xlsx";
            File file = new File(path);
            XSSFWorkbook excel;
            XSSFSheet sheet;

            if (!file.exists()) {
                excel = new XSSFWorkbook();
                sheet = excel.createSheet("sheet 1");
=======
import com.kodigo.airport.model.Incident;
import com.kodigo.airport.model.Flight;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
@Service
public class ExcelReport implements IFileWrite{
    private void doHeader(){
        String path = "./report.xlsx";
        File file = new File(path);
        try{
            if (!file.exists()) {
                XSSFWorkbook excel = new XSSFWorkbook();
                XSSFSheet sheet = excel.createSheet("sheet 1");
>>>>>>> alpha
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
<<<<<<< HEAD

                excel.write(new FileOutputStream(path));
            }
            excel = new XSSFWorkbook(new FileInputStream(file));
            sheet = excel.getSheetAt(0);
            int getNumRow = sheet.getLastRowNum();

=======
                excel.write(new FileOutputStream(path));
                excel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean write(List<Incident> incidentList, List<Flight> flightList,String weather) {
        boolean success = false;
        String path = "./report.xlsx";
        doHeader();
        try{
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook excel = new XSSFWorkbook(file);
            XSSFSheet sheet = excel.getSheetAt(0);
            int getNumRow = sheet.getLastRowNum();
>>>>>>> alpha
            for(int i = getNumRow; i > 0; i--){
                sheet.removeRow(sheet.getRow(i));
            }
            int numRow = 1;
            int numCol = 9;
<<<<<<< HEAD

            for (Flight flight : flightList) {

                String departureCity = flight.getDepartureCity().getCityName() + ", ";
                departureCity += flight.getDepartureCity().getCountry().getCountryName();

                String arrivalCity = flight.getArrivalCity().getCityName() + ", ";
                arrivalCity += flight.getArrivalCity().getCountry().getCountryName();

=======
            for (Flight flight : flightList) {
                String departureCity = flight.getDepartureCity().getCityName() + ", ";
                departureCity += flight.getDepartureCity().getCountry().getCountryName();
                String arrivalCity = flight.getArrivalCity().getCityName() + ", ";
                arrivalCity += flight.getArrivalCity().getCountry().getCountryName();
>>>>>>> alpha
                Row row = sheet.createRow(numRow);
                row.createCell(0).setCellValue(flight.getIdFlight().toString());
                row.createCell(1).setCellValue(flight.getAirline().getAirlineName());
                row.createCell(2).setCellValue(flight.getAirplane().getModel());
                row.createCell(3).setCellValue(departureCity);
                row.createCell(4).setCellValue(arrivalCity);
                row.createCell(5).setCellValue(flight.getDepartureTime());
                row.createCell(6).setCellValue(flight.getArrivalTime());
                row.createCell(7).setCellValue(flight.getStatus());
                row.createCell(8).setCellValue(weather);
<<<<<<< HEAD

                for (Incident incident : incidentList) {

                    if (flight.getIdFlight() == incident.getFlight().getIdFlight()) {

=======
                for (Incident incident : incidentList) {
                    if (flight.getIdFlight().equals(incident.getFlight().getIdFlight())) {
>>>>>>> alpha
                        String incidentString = incident.getDescription() + ". ";
                        incidentString += incident.getDateTime();
                        row.createCell(numCol).setCellValue(incidentString);
                        numCol += 1;
                    }
                }
                numCol= 9;
                numRow += 1;
            }
            excel.write(new FileOutputStream(path));
            success = true;
<<<<<<< HEAD

        }catch(IOException e){
            System.out.println("An error occurred: "+e.getMessage());
        }
        return success;
    }
}
=======
            excel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return success;
    }
}
>>>>>>> alpha

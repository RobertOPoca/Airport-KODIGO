package com.kodigo.airport.utils;
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
            for(int i = getNumRow; i > 0; i--){
                sheet.removeRow(sheet.getRow(i));
            }
            int numRow = 1;
            int numCol = 9;
            for (Flight flight : flightList) {
                String departureCity = flight.getDepartureCity().getCityName() + ", ";
                departureCity += flight.getDepartureCity().getCountry().getCountryName();
                String arrivalCity = flight.getArrivalCity().getCityName() + ", ";
                arrivalCity += flight.getArrivalCity().getCountry().getCountryName();
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
                for (Incident incident : incidentList) {
                    if (flight.getIdFlight().equals(incident.getFlight().getIdFlight())) {
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
            excel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return success;
    }
}
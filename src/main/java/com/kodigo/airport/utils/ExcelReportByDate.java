package com.kodigo.airport.utils;

import com.kodigo.airport.model.Incident;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelReportByDate implements IFileWrite{
    @Override
    public boolean write(List<Incident> incidents, String weather) {
        boolean success = false;
        try{
            String path = ".xlsx";//incidents.get(0).toString();
            //path += ".xlsx";
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
                row.createCell(7).setCellValue("Status");
                row.createCell(8).setCellValue("Incidents");
                excel.write(new FileOutputStream(path));
            }
            excel = new XSSFWorkbook(new FileInputStream(file));
            sheet = excel.getSheetAt(0);
            Map<Integer, Integer> exits = new HashMap();
            for(Incident incident: incidents){
                //if(exits.containsKey(incident.getIdFlight().getIdFlight())){

                //}
            }
            success = true;

        }catch(IOException e){
            System.out.println("An error occurred: "+e.getMessage());
        }
        return success;
    }
}

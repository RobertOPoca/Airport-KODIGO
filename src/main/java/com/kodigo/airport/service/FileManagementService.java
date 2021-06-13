package com.kodigo.airport.service;

import com.kodigo.airport.utils.ExcelBatch;
import com.kodigo.airport.utils.ExcelReportByDate;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileManagementService {
    @Autowired
    private ExcelBatch excelBatch;
    @Autowired
    private ExcelReportByDate excelReportByDate;

    public void readFile(){
        this.excelBatch.read();
    }

    public void writeFile(List<Incident> incidents, String weather){
        this.excelReportByDate.write(incidents, weather);
    }
}

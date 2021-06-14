package com.kodigo.airport.service;

import com.kodigo.airport.utils.ExcelBatch;
import com.kodigo.airport.utils.ExcelReportByDate;
import com.kodigo.airport.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileManagementService {
    @Autowired
    private ExcelBatch excelBatch = new ExcelBatch();
    @Autowired
    private ExcelReportByDate excelReportByDate;

    public boolean readFile(){
        return this.excelBatch.read();
    }

    public void writeFile(List<Incident> incidents, String weather){
        this.excelReportByDate.write(incidents, weather);
    }
}

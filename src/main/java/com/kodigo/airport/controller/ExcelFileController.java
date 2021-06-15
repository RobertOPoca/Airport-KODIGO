package com.kodigo.airport.controller;

import com.kodigo.airport.service.FileManagementService;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/excel")
public class ExcelFileController {
    @Autowired
    private FileManagementService fileManagement;

    @Autowired
    private MailService mailService;

    @GetMapping
    public ResponseApi read(){
        boolean success = fileManagement.readFile();
        String message = success? "Flights was created successfully": "Error" ;
        return new ResponseApi(success, message);
    }

    @PostMapping
    public ResponseApi sendExcelReportByDate(@RequestBody String date){
        boolean success;
        success = fileManagement.excelReportByDate(date, "Cold");
        String message = "File error";
        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Error";
        }
        return new ResponseApi(success, message);
    }

    @PutMapping
    public ResponseApi sendExcelReportById(@RequestBody int id){
        boolean success;
        success = fileManagement.excelReportById(id, "Cloudy");
        String message = "File error";
        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Error";
        }
        return new ResponseApi(success, message);
    }
}

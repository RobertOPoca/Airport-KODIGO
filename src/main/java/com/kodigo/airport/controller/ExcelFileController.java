package com.kodigo.airport.controller;

import com.kodigo.airport.dto.BodyReportDTO;
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
    public ResponseApi sendExcelReportByDate(@RequestBody BodyReportDTO bodyReportDTO){
        boolean success;
        success = fileManagement.excelReportByDate(bodyReportDTO);
        String message = "File error";

        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Error";
        }
        return new ResponseApi(success, message);
    }

    @PutMapping
    public ResponseApi sendExcelReportById(@RequestBody BodyReportDTO bodyReportDTO){
        boolean success;
        success = fileManagement.excelReportById(bodyReportDTO);
        String message = "File error";

        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Error";
        }
        return new ResponseApi(success, message);
    }
}

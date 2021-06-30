package com.kodigo.airport.controller;

import com.kodigo.airport.service.ApiService;
import com.kodigo.airport.service.FileManagementService;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.MailService;
<<<<<<< HEAD
=======
import com.kodigo.airport.utils.ApiConnection;
import com.kodigo.airport.utils.ApiWeather;
>>>>>>> alpha
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@RestController
@RequestMapping("/excel")
public class ExcelFileController {
    @Autowired
    private FileManagementService fileManagement;

    @Autowired
    private MailService mailService;

    @GetMapping
<<<<<<< HEAD
    public ResponseApi read(){
=======
    public ResponseApi<String> read(){
>>>>>>> alpha
        boolean success = fileManagement.readFile();
        String message = success? "Flights was created successfully": "Error" ;
        return new ResponseApi<>(success, message);
    }

    @PostMapping()
    public ResponseApi<String> sendExcelReportByDate(@RequestBody String date){
        boolean success;
        ApiService apiService = new ApiService(new ApiWeather());
        ResponseApi<String> responseApi = apiService.getWeather();
        success = fileManagement.excelReportByDate(date, responseApi.getData());
        String message = "File error";
        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Mail Error";
        }
        return new ResponseApi<>(success, message);
    }

    @PutMapping("/{id}")
    public ResponseApi sendExcelReportById(@PathVariable("id") Integer id){
        boolean success;
        ApiService apiService = new ApiService(new ApiWeather());
        ResponseApi<String> responseApi = apiService.getWeather();
        success = fileManagement.excelReportById(id, responseApi.getData());
        String message = "File error";
        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Mail Error";
        }
        return new ResponseApi(success, message);
    }

    @PostMapping
    public ResponseApi sendExcelReportByDate(@RequestBody String date){
        boolean success;
        success = fileManagement.excelReportByDate(date, "Cold");
        String message = "File error";
        if(success){
            success = mailService.sendMail();
            message = success? "Mail sent successfully" : "Mail Error";
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
            message = success? "Mail sent successfully" : "Mail Error";
        }
        return new ResponseApi(success, message);
    }
}

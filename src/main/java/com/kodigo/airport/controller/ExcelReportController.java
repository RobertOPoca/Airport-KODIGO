package com.kodigo.airport.controller;

import com.kodigo.airport.service.ApiService;
import com.kodigo.airport.service.ExcelReportService;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.MailService;
import com.kodigo.airport.utils.ApiWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ExcelReportController {

    @Autowired
    private ExcelReportService excelReportService;

    @Autowired
    private MailService mailService;


    private boolean generateReportAndSendMail(String date, String weather){
        if( excelReportService.generateReport(date, weather) ){
            return mailService.sendMail();
        }else{
            return false;
        }
    }

    private boolean generateReportAndSendMail(int id, String weather){
        if(excelReportService.generateReport(id, weather)){
            return mailService.sendMail();
        }else{
            return false;
        }
    }

    private String getWeather(){
        ApiService apiService = new ApiService(new ApiWeather());
        ResponseApi<String> responseApi = apiService.getWeather();
        return responseApi.getData();
    }

    @PostMapping
    public ResponseApi<String> sendExcelReportByDate(@RequestBody String date){

        boolean success = generateReportAndSendMail(date, getWeather());
        String message = success? "Mail sent successfully" : "An error occurred";
        return new ResponseApi<>(success, message);
    }

    @PutMapping("/{id}")
    public ResponseApi<String> sendExcelReportById(@PathVariable("id") Integer id){

        boolean success = generateReportAndSendMail(id, getWeather());
        String message = success? "Mail sent successfully" : "An error occurred";
        return new ResponseApi<>(success, message);
    }
}

package com.kodigo.airport.controller;

import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.utils.ExcelBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class ExcelBatchController {

    @Autowired
    ExcelBatch excelBatch;

    @GetMapping
    public ResponseApi<String> read(){
        boolean success = excelBatch.read();
        String message = success? "Flights was created successfully": "Error" ;
        return new ResponseApi<>(success, message);
    }
}

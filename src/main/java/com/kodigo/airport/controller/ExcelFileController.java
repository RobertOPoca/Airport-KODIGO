package com.kodigo.airport.controller;

import com.kodigo.airport.service.FileManagementService;
import com.kodigo.airport.responses.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/excel")
public class ExcelFileController {
    @Autowired
    private FileManagementService fileManagement;

    @PostMapping
    public ResponseApi read(){
        String message = fileManagement.readFile();
        return new ResponseApi(true, message);
    }
}

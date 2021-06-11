package com.kodigo.airport.controllers;

import com.kodigo.airport.dto.AirlineDTO;
import com.kodigo.airport.items.ItemAirline;
import com.kodigo.airport.models.Airline;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping()
    public ResponseApi<List<ItemAirline>> getAllAirlines() {
        Boolean success;
        String message;
        List<ItemAirline> itemAirlineList = new ArrayList<>();
        List<Airline> airlineList= airlineService.getAllAirlines();
        if(airlineList.isEmpty()){
            success = false;
            message = "No airlines found";
        }else{
            success = true;
            message = "Success";
            for(Airline airline : airlineList){
                var itemAirline = new ItemAirline();
                itemAirline.setIdAirline(airline.getIdAirline());
                itemAirline.setName(airline.getAirlineName());
                itemAirlineList.add(itemAirline);
            }
        }
        return new ResponseApi <>(success, message, itemAirlineList);
    }

    @PostMapping()
    @ResponseBody
    public ResponseApi<String> saveAirline(@RequestBody AirlineDTO airlineDto) {
        Boolean success = false;
        String message = "";
        Airline airline = new Airline();
        try{
            if(airlineDto.getId()>0){
                Optional<Airline> optionalAirline = this.airlineService.getAirlineById(airlineDto.getId());
                if(optionalAirline.isPresent())
                    airline = optionalAirline.get();
            }

            airline.setAirlineName(airlineDto.getAirline());
            if(this.airlineService.saveOrUpdateAirline(airline)!=null){
                success = true;
                message = "Airline was created successfully";
            }else{
                success = false;
                message = "Error";

            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }

        return new ResponseApi<>(success, message);
    }

}

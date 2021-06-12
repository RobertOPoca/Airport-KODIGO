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

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping()
    public ResponseApi<List<ItemAirline>> getAllAirlines() {
        Boolean success;
        String message;
        List<ItemAirline> itemAirlineList = new ArrayList<>();
        List<Airline> airlineList= airlineService.findAll();
        if(airlineList.isEmpty()){
            success = false;
            message = "No airlines found";
        }else{
            success = true;
            message = "Airlines found";
            for(Airline airline : airlineList){
                var itemAirline = new ItemAirline();
                itemAirline.setIdAirline(airline.getIdAirline());
                itemAirline.setAirlineName(airline.getAirlineName());
                itemAirlineList.add(itemAirline);
            }
        }
        return new ResponseApi <>(success, message, itemAirlineList);
    }

    @PostMapping
    public ResponseApi<ItemAirline> create(@RequestBody AirlineDTO airlineDTO){
        Boolean success = false;
        String message = "";
        Airline airline = new Airline();
        ItemAirline itemAirline = new ItemAirline();
        try{
            airline.setAirlineName(airlineDTO.getAirlineName());
            airline = this.airlineService.create(airline);
            if(airline!=null){
                itemAirline.setIdAirline(airline.getIdAirline());
                itemAirline.setAirlineName(airline.getAirlineName());
                success = true;
                message = "Airline was created successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemAirline);
    }

    @PutMapping
    public ResponseApi<ItemAirline>  update(@RequestBody AirlineDTO airlineDTO) {
        Boolean success = false;
        String message = "";
        Airline airline = new Airline();
        ItemAirline itemAirline = new ItemAirline();
        try{
            airline.setIdAirline(airlineDTO.getIdAirline());
            airline.setAirlineName(airlineDTO.getAirlineName());
            airline = this.airlineService.update(airline);
            if(airline!=null){
                itemAirline.setIdAirline(airline.getIdAirline());
                itemAirline.setAirlineName(airline.getAirlineName());
                success = true;
                message = "Airline was updated successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemAirline);
    }

    @GetMapping("/{id}")
    public ResponseApi<ItemAirline> findById(@PathVariable("id") Integer idAirline){
        Boolean success;
        String message;
        ItemAirline itemAirline = new ItemAirline();
        Airline airline = airlineService.findById(idAirline);
        if(airline==null){
            success = false;
            message = "No airlines found";
        }else{
            success = true;
            message = "Airlines found";

            itemAirline.setIdAirline(airline.getIdAirline());
            itemAirline.setAirlineName(airline.getAirlineName());
        }
        return new ResponseApi<>(success, message, itemAirline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idAirline){
        airlineService.delete(idAirline);
    }


}

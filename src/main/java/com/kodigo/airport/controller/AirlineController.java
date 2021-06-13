package com.kodigo.airport.controller;

import com.kodigo.airport.dto.AirlineDTO;
import com.kodigo.airport.item.IItemAirline;

import com.kodigo.airport.model.Airline;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.AirlineService;

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
    public ResponseApi<List<IItemAirline>> getAllAirlines() {
        boolean success;
        String message;
        List<IItemAirline> itemAirlineList = new ArrayList<>();
        List<Airline> airlineList= airlineService.findAll();
        if(airlineList.isEmpty()){
            success = false;
            message = "No airlines found";
        }else{
            success = true;
            message = "Airlines found";
            for(Airline airline : airlineList){
                IItemAirline itemAirline = new IItemAirline();
                itemAirline.setIdAirline(airline.getIdAirline());
                itemAirline.setAirlineName(airline.getAirlineName());
                itemAirlineList.add(itemAirline);
            }
        }
        return new ResponseApi <>(success, message, itemAirlineList);

    }

    @PostMapping
    public ResponseApi<IItemAirline> create(@RequestBody AirlineDTO airlineDTO){
        boolean success = false;
        String message = "";
        Airline airline = new Airline();
        IItemAirline itemAirline = new IItemAirline();
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

    public ResponseApi<IItemAirline>  update(@RequestBody AirlineDTO airlineDTO) {
        boolean success = false;
        String message = "";
        Airline airline = new Airline();
        IItemAirline itemAirline = new IItemAirline();
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
    public ResponseApi<IItemAirline> findById(@PathVariable("id") Integer idAirline){
        boolean success;
        String message;
        IItemAirline itemAirline = new IItemAirline();
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

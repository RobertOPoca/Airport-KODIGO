package com.kodigo.airport.controllers;

import com.kodigo.airport.dto.AirlineDTO;
import com.kodigo.airport.models.Airline;
import com.kodigo.airport.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> findAll(){
        return airlineService.findAll();
    }

    @PostMapping
    public Airline create(@RequestBody AirlineDTO airlineDTO){
        Airline airline = new Airline();
        airline.setAirlineName(airlineDTO.getAirlineName());
        return airlineService.create(airline);
    }

    @PutMapping
    public Airline update(@RequestBody Airline airline) {
        //Airline airline = new Airline();
        //airline.setIdAirline(airlineDTO.getIdAirline());
        //airline.setAirlineName(airlineDTO.getAirlineName());
        return airlineService.update(airline);
    }

    @GetMapping("/{id}")
    public Airline findById(@PathVariable("id") Integer idAirline){
        return airlineService.findById(idAirline);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idAirline){
        airlineService.delete(idAirline);
    }


}

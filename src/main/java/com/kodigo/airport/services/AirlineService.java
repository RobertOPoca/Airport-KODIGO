package com.kodigo.airport.services;

import com.kodigo.airport.models.Airline;
import com.kodigo.airport.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines(){
        return (List<Airline>) this.airlineRepository.findAll();
    }
    public Optional<Airline> getAirlineById(Integer id){
        return this.airlineRepository.findById(id);
    }
    public Airline saveOrUpdateAirline(Airline airline){
        return this.airlineRepository.save(airline);
    }
}

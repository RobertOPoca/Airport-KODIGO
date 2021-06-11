package com.kodigo.airport.services;

import com.kodigo.airport.models.Airplane;
import com.kodigo.airport.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> getAllAirlines(){
        return (List<Airplane>) airplaneRepository.findAll();
    }
    public Optional<Airplane> getAirlineByModel(String model){
        return airplaneRepository.findById(model);
    }
}

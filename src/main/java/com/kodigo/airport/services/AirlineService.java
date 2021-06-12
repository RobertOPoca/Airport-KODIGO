package com.kodigo.airport.services;

import com.kodigo.airport.models.Airline;
import com.kodigo.airport.repositories.IAirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService implements IAirlineService{

    @Autowired
    private IAirlineRepository airlineRepo;

    @Override
    public Airline create(Airline airline) {
        return airlineRepo.save(airline);
    }

    @Override
    public Airline update(Airline airline) {
        Airline airlineTMP = findById(airline.getIdAirline());
        airlineTMP.setAirlineName(airline.getAirlineName());
        return airlineRepo.save(airlineTMP);
    }

    @Override
    public Airline findById(Integer id) {
        Optional<Airline> airlineOptional = airlineRepo.findById(id);
        return airlineOptional.orElse(null); // if the object not found... return null
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        airlineRepo.deleteById(id);
    }
}

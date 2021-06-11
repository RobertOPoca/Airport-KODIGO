package com.kodigo.airport.services;

import com.kodigo.airport.models.Flight;
import com.kodigo.airport.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights(){
        return (List<Flight>) flightRepository.findAll();
    }
    public Optional<Flight> getFlightById(Integer id){
        return flightRepository.findById(id);
    }
    public Flight saveFlight(Flight flight){
        return flightRepository.save(flight);
    }

}

package com.kodigo.airport.service;

import com.kodigo.airport.model.Flight;
import com.kodigo.airport.repository.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements  IFlightService{


    @Autowired
    private IFlightRepository flightRepo;

    @Override
    public Flight create(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public Flight update(Flight flight) {
        Flight flightTMP = findById(flight.getIdFlight());
        flightTMP.setAirplane(flight.getAirplane());
        flightTMP.setAirline(flight.getAirline());
        flightTMP.setDepartureCity(flight.getDepartureCity());
        flightTMP.setArrivalCity(flight.getArrivalCity());
        flightTMP.setStatus(flight.getStatus());
        flightTMP.setDepartureTime(flight.getDepartureTime());
        flightTMP.setArrivalTime(flight.getArrivalTime());

        return flightRepo.save(flightTMP);
    }

    @Override
    public Flight findById(Integer id) {
        Optional<Flight> flightOptional = flightRepo.findById(id);
        return flightOptional.orElse(null);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        flightRepo.deleteById(id);
    }
}

package com.kodigo.airport.services;

import com.kodigo.airport.models.Airline;

import java.util.List;

public interface IAirlineService {

    Airline create(Airline airline);

    Airline update(Airline airline);

    Airline findById(Integer id);

    List<Airline> findAll();

    void delete(Integer id);

}

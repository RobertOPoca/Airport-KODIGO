package com.kodigo.airport.service;

import com.kodigo.airport.model.Flight;
import java.util.List;

public interface IFlightService {

    Flight create(Flight flight);

    Flight update(Flight flight);

    Flight findById(Integer id);

    List<Flight> findAll();

    void delete(Integer id);

    List<Flight> getDateBetweenDate(String start, String end);
}

package com.kodigo.airport.service;

import com.kodigo.airport.model.Airplane;

import java.util.List;

public interface IAirplaneService {

    Airplane create(Airplane airplane);

    Airplane update(Airplane airplane);

    Airplane findById(String model);

    List<Airplane> findAll();

    void delete(String model);

}

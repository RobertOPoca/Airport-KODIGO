package com.kodigo.airport.service;

import com.kodigo.airport.model.Incident;

import java.util.List;

public interface IIncidentService {
    Incident create(Incident flight);

    Incident update(Incident flight);

    Incident findById(Integer id);

    List<Incident> findAll();

    void delete(Integer id);
}

package com.kodigo.airport.service;


import com.kodigo.airport.model.City;

import java.util.List;

public interface ICityService {

    City create(City city);

    City update(City city);

    City findById(Integer id);

    List<City> findAll();

    void delete(Integer id);

}

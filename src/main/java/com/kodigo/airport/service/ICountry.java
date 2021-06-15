package com.kodigo.airport.service;

import com.kodigo.airport.model.Country;
import java.util.List;

public interface ICountry {

    Country create(Country country);

    Country update(Country country);

    Country findById(Integer id);

    List<Country> findAll();

    void delete(Integer id);
}

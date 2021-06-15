package com.kodigo.airport.service;

import com.kodigo.airport.model.City;
import com.kodigo.airport.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService{

    @Autowired
    private ICityRepository cityRepo;

    @Override
    public City create(City city) {
        return cityRepo.save(city);
    }

    @Override
    public City update(City city) {
        City cityTMP = findById(city.getIdCity());
        cityTMP.setCityName(city.getCityName());
        return cityRepo.save(cityTMP);
    }

    @Override
    public City findById(Integer id) {
        Optional<City> cityOptional = cityRepo.findById(id);
        return cityOptional.orElse(null);
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        cityRepo.deleteById(id);
    }
}

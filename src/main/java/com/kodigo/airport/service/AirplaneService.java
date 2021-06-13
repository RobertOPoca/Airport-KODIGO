package com.kodigo.airport.service;

import com.kodigo.airport.model.Airplane;
import com.kodigo.airport.repository.IAirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService implements IAirplaneService{

    @Autowired
    private IAirplaneRepository airplaneRepo;

    @Override
    public Airplane create(Airplane airplane) {

        return airplaneRepo.save(airplane);
    }

    @Override
    public Airplane update(Airplane airplane) {
        Airplane airplaneTMP = findById(airplane.getModel());
        airplaneTMP.setReach(airplane.getReach());
        return airplaneRepo.save(airplaneTMP);
    }

    @Override
    public Airplane findById(String model) {
        Optional<Airplane> airplaneOptional = airplaneRepo.findById(model);
        return airplaneOptional.orElse(null);
    }

    @Override
    public List<Airplane> findAll() {
        return airplaneRepo.findAll();
    }

    @Override
    public void delete(String model) {
        airplaneRepo.deleteById(model);
    }
}

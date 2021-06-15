package com.kodigo.airport.service;

import com.kodigo.airport.model.Country;
import com.kodigo.airport.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements ICountry{

    @Autowired
    private ICountryRepository countryRepo;


    @Override
    public Country create(Country country) {
        return countryRepo.save(country);
    }

    @Override
    public Country update(Country country) {
        Country countryTMP = findById(country.getIdCountry());
        countryTMP.setCountryName(country.getCountryName());
        return countryRepo.save(countryTMP);
    }

    @Override
    public Country findById(Integer id) {
        Optional<Country> countryOptional= countryRepo.findById(id);
        return countryOptional.orElse(null);
    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    @Override
    public void delete(Integer id) {
        countryRepo.deleteById(id);
    }
}

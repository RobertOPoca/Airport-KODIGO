package com.kodigo.airport.controller;

import com.kodigo.airport.dto.AirlineDTO;
import com.kodigo.airport.dto.CountryDTO;
import com.kodigo.airport.item.IItemAirline;
import com.kodigo.airport.item.IItemCountry;
import com.kodigo.airport.model.Airline;
import com.kodigo.airport.model.Country;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping()
    public ResponseApi<List<IItemCountry>> getAllContries() {
        boolean success;
        String message;
        List<IItemCountry> itemCountryList = new ArrayList<>();
        List<Country> countryList= countryService.findAll();
        if(countryList.isEmpty()){
            success = false;
            message = "No countries found";
        }else{
            success = true;
            message = "Countries found";
            for(Country country : countryList){
                var itemCountry = new IItemCountry();
                itemCountry.setIdCountry(country.getIdCountry());
                itemCountry.setCountryName(country.getCountryName());
                itemCountryList.add(itemCountry);
            }
        }
        return new ResponseApi <>(success, message, itemCountryList);
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemCountry> findById(@PathVariable("id") Integer idCountry){
        boolean success;
        String message;
        IItemCountry itemCountry = new IItemCountry();
        Country country = countryService.findById(idCountry);
        if(country==null){
            success = false;
            message = "No country found";
        }else{
            success = true;
            message = "Country found";

            itemCountry.setIdCountry(country.getIdCountry());
            itemCountry.setCountryName(country.getCountryName());
        }
        return new ResponseApi<>(success, message, itemCountry);
    }

    @PutMapping
    public ResponseApi<IItemCountry>  update(@RequestBody CountryDTO countryDTO) {
        boolean success = false;
        String message = "";
        Country country = new Country();
        IItemCountry itemCountry = new IItemCountry();
        try{
            country.setIdCountry(countryDTO.getIdCountry());
            country.setCountryName(countryDTO.getCountryName());
            country = this.countryService.update(country);
            if(country!=null){
                itemCountry.setIdCountry(countryDTO.getIdCountry());
                itemCountry.setCountryName(country.getCountryName());
                success = true;
                message = "Country was updated successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemCountry);
    }

}

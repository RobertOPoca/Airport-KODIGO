package com.kodigo.airport.controller;

import com.kodigo.airport.dto.CityDTO;
import com.kodigo.airport.item.IItemCity;
import com.kodigo.airport.model.City;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping()
    public ResponseApi<List<IItemCity>> getAllCities(){
        boolean success;
        String message;
        List<IItemCity> itemCityList = new ArrayList<>();
        List<City> cityList = cityService.findAll();
        if(cityList.isEmpty()){
            success = false;
            message ="No Cities found";
        }else{
            success = true;
            message = "Cities found";
            for(City city : cityList){
                var itemCity = new IItemCity();
                itemCity.setIdCity(city.getIdCity());
                itemCity.setCityName(city.getCityName());
                itemCityList.add(itemCity);
            }
        }
        return new ResponseApi<>(success, message, itemCityList);
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemCity> findById(@PathVariable("id") Integer idCity){
        boolean success;
        String message;
        IItemCity itemCity = new IItemCity();
        City city = cityService.findById(idCity);
        if(city==null){
            success = false;
            message = "No City found";
        }else{
            success = true;
            message = "City found";

            itemCity.setIdCity(city.getIdCity());
            itemCity.setCityName(city.getCityName());
        }
        return new ResponseApi<>(success, message, itemCity);

    }

    @PostMapping
    public ResponseApi<IItemCity> create(@RequestBody CityDTO cityDTO){
        boolean success = false;
        String message = "";
        City city = new City();
        IItemCity itemCity = new IItemCity();
        try {
            city.setCityName(cityDTO.getCityName());
            city = this.cityService.create(city);
            if (city != null) {
                itemCity.setIdCity(city.getIdCity());
                itemCity.setCityName(city.getCityName());
                success = true;
                message = "City was created successfully";
            } else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
                ex.printStackTrace();
                message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemCity);
    }

    @PutMapping
    public ResponseApi<IItemCity> update(@RequestBody CityDTO cityDTO){
        boolean success = false;
        String message = "";
        City city = new City();
        IItemCity itemCity = new IItemCity();
        try {
            city.setIdCity(cityDTO.getIdCity());
            city.setCityName(cityDTO.getCityName());
            city = this.cityService.update(city);
            if (city != null) {
                itemCity.setIdCity(city.getIdCity());
                itemCity.setCityName(city.getCityName());
                success = true;
                message = "City was aupdated successfully";
            } else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemCity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idCity){
        cityService.delete(idCity);
    }
}

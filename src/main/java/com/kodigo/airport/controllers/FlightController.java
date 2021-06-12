package com.kodigo.airport.controllers;

import com.kodigo.airport.items.ItemFlight;
import com.kodigo.airport.models.Flight;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/flights")
    public ResponseApi<List<ItemFlight>> getAllRoles() {
        Boolean success;
        String message;
        List<ItemFlight> itemFlightList = new ArrayList<>();
        List<Flight> flightList= flightService.getAllFlights();
        if(flightList.isEmpty()){
            success = false;
            message = "No flights found";
        }else{
            success = true;
            message = "Success";
            for(Flight flight : flightList){
                var itemFlight = new ItemFlight();
                itemFlight.setId(flight.getIdFlight());
                //itemFlight.setAirline(flight.getAirlineAirplane().getAirline().getAirlineName());
                //itemFlight.setAirplane(flight.getAirlineAirplane().getAirplane().getModel());
                itemFlight.setSourceCity(flight.getCity().getCityName());
                itemFlight.setSourceCountry(flight.getCity().getCountry().getCountryName());
                itemFlightList.add(itemFlight);

            }
        }
        return new ResponseApi <>(success, message, itemFlightList);
    }
}

package com.kodigo.airport.controller;

import com.kodigo.airport.dto.FlightDTO;
import com.kodigo.airport.item.IItemFlight;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping()
    public ResponseApi<List<IItemFlight>> getAllFlights(){
        boolean success;
        String message;
        List<IItemFlight> itemFlightList = new ArrayList<>();
        List<Flight> flightList = flightService.findAll();
        if(flightList.isEmpty()){
            success = false;
            message = "No flights found";
        }else{
            success = true;
            message = "Flights found";
            for(Flight flight: flightList){
                var itemFlight = new IItemFlight();

                itemFlight.setIdFlight(flight.getIdFlight());
                itemFlight.setModel(flight.getModel());

                itemFlight.setAirline(flight.getAirline().getAirlineName());
                itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


                itemFlight.setIdDepartureCity(flight.getIdDepartureCity().toString());//idCity
                itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
                itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
                itemFlight.setDepartureDate(flight.getDepartureTime().toString()); //date
                itemFlight.setDepartureTime(String.valueOf(flight.getDepartureTime().getTime())); //time


                itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
                itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
                itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
                itemFlight.setArrivalDate(flight.getArrivalTime().toString());//date
                itemFlight.setArrivalTime(String.valueOf(flight.getArrivalTime().getTime()));//time

                itemFlight.setStatus(flight.getStatus());
                itemFlightList.add(itemFlight);
            }
        }
        return new ResponseApi<>(success, message, itemFlightList);
    }
    @PostMapping
    public ResponseApi<IItemFlight> create(@RequestBody FlightDTO flightDTO){
        boolean success;
        success = false;
        String message;
        message = "";
        Flight flight;
        flight = new Flight();
        IItemFlight itemFlight;
        itemFlight = new IItemFlight();
        try{
            flight.setModel(flightDTO.getModel());
            flight.setIdAirline(flightDTO.getIdAirline());
            flight.setIdDepartureCity(flightDTO.getIdDepartureCity());
            flight.setIdArrivalCity(flightDTO.getIdArrivalCity());
            flight.setStatus(flightDTO.getStatus());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivalTime(flightDTO.getArrivalTime());


            flight = this.flightService.create(flight);
            if(flight!=null){
                itemFlight.setIdFlight(flight.getIdAirline());
                itemFlight.setModel(flight.getModel());
                itemFlight.setAirline(flight.getAirline().toString());
                itemFlight.setDepartureCity(flight.getDepartureCity().toString());
                itemFlight.setDestinationCity(flight.getArrivalCity().toString());
                itemFlight.setStatus(flight.getStatus());
                itemFlight.setDepartureTime(flight.getDepartureTime().toString());
                itemFlight.setArrivalTime(flight.getArrivalTime().toString());

                success = true;
                message = "Airline was created successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemFlight);
    }

    @PutMapping

    public ResponseApi<IItemFlight>  update(@RequestBody FlightDTO flightDTO) {
        boolean success = false;
        String message = "";
        Flight flight = new Flight();
        IItemFlight itemFlight = new IItemFlight();
        try{
            flight.setIdAirline(flightDTO.getIdFlight());
            flight.setModel(flightDTO.getModel());
            flight.setIdAirline(flightDTO.getIdAirline());
            flight.setIdDepartureCity(flightDTO.getIdDepartureCity());
            flight.setIdArrivalCity(flightDTO.getIdArrivalCity());
            flight.setStatus(flightDTO.getStatus());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivalTime(flightDTO.getArrivalTime());

            flight = this.flightService.update(flight);
            if(flight!=null){
                itemFlight.setIdFlight(flight.getIdAirline());
                itemFlight.setModel(flight.getModel());
                itemFlight.setAirline(flight.getAirline().toString());
                itemFlight.setDepartureCity(flight.getDepartureCity().toString());
                itemFlight.setDestinationCity(flight.getArrivalCity().toString());
                itemFlight.setStatus(flight.getStatus());
                itemFlight.setDepartureTime(flight.getDepartureTime().toString());
                itemFlight.setArrivalTime(flight.getArrivalTime().toString());
                success = true;
                message = "Flight was updated successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemFlight);
    }
    @GetMapping("/{id}")
    public ResponseApi<IItemFlight> findById(@PathVariable("id") Integer idFlight){
        boolean success;
        String message;
        IItemFlight itemFlight = new IItemFlight();
        Flight flight = flightService.findById(idFlight);
        if(flight==null){
            success = false;
            message = "No flights found";
        }else{
            success = true;
            message = "Flights found";

            itemFlight.setIdFlight(flight.getIdFlight());
            itemFlight.setModel(flight.getModel());

            itemFlight.setAirline(flight.getAirline().getAirlineName());
            itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


            itemFlight.setIdDepartureCity(flight.getIdDepartureCity().toString());//idCity
            itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
            itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
            itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
            itemFlight.setDepartureDate(flight.getDepartureTime().toString()); //date
            itemFlight.setDepartureTime(String.valueOf(flight.getDepartureTime().getTime())); //time


            itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
            itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
            itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
            itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
            itemFlight.setArrivalDate(flight.getArrivalTime().toString());//date
            itemFlight.setArrivalTime(String.valueOf(flight.getArrivalTime().getTime()));//time

            itemFlight.setStatus(flight.getStatus());
        }
        return new ResponseApi<>(success, message, itemFlight);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idFlight){
        flightService.delete(idFlight);
    }
}

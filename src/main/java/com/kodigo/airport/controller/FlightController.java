package com.kodigo.airport.controller;

import com.kodigo.airport.dto.FlightDTO;
import com.kodigo.airport.item.IItemFlight;
import com.kodigo.airport.model.Airline;
import com.kodigo.airport.model.Airplane;
import com.kodigo.airport.model.City;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.AirlineService;
import com.kodigo.airport.service.AirplaneService;
import com.kodigo.airport.service.CityService;
import com.kodigo.airport.service.FlightService;
import com.kodigo.airport.utils.MyFormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AirlineService airlineService;

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
            itemFlightList = showAllFlights(flightList, itemFlightList);
        }
        return new ResponseApi<>(success, message, itemFlightList);
    }
    public  List<IItemFlight> showAllFlights(@org.jetbrains.annotations.NotNull List<Flight> flightList, List<IItemFlight> itemFlightList ){
        for(Flight flight: flightList){
            IItemFlight itemFlight = new IItemFlight();
            itemFlight = showFlight(flight, itemFlight);
            itemFlightList.add(itemFlight);
        }
        return itemFlightList;
    }

    @PostMapping
    public ResponseApi<IItemFlight> create(@RequestBody FlightDTO flightDTO){
        boolean success;
        success = false;
        String message;
        message = "Error";
        Flight flight;
        flight = new Flight();
        IItemFlight itemFlight = new IItemFlight();
        try{
            flight=createFlight(flight, flightDTO);
            if(flight!=null){
                itemFlight = showFlight(flight, itemFlight);
                success = true;
                message = "Airline was created successfully";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemFlight);
    }
    public Flight createFlight(Flight flight, FlightDTO flightDTO){
        Airplane airplane = airplaneService.findById(flightDTO.getModel());
        flight.setAirplane(airplane);
        Airline airline= airlineService.findById(flightDTO.getIdAirline());
        flight.setAirline(airline);
        City cityDeparture = cityService.findById(flightDTO.getIdDepartureCity());
        flight.setDepartureCity(cityDeparture);
        City cityArrival = cityService.findById(flightDTO.getIdArrivalCity());
        flight.setArrivalCity(cityArrival);
        flight.setStatus(flightDTO.getStatus());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight = this.flightService.create(flight);
        return flight;
    }

    @PutMapping
    public ResponseApi<IItemFlight>  update(@RequestBody FlightDTO flightDTO) {
        boolean success;
        success = false;
        String message;
        message = "Error";
        Flight flight;
        flight = new Flight();
        IItemFlight itemFlight = new IItemFlight();
        try{
            flight = updateFlight(flight, flightDTO);
            if(flight!=null){
                itemFlight = showFlight(flight, itemFlight);
                success = true;
                message = "Flight was updated successfully";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemFlight);
    }
    public Flight updateFlight(Flight flight, FlightDTO flightDTO){
        Airplane airplane = airplaneService.findById(flightDTO.getModel());
        flight.setAirplane(airplane);
        Airline airline= airlineService.findById(flightDTO.getIdAirline());
        flight.setAirline(airline);
        City cityDeparture = cityService.findById(flightDTO.getIdDepartureCity());
        flight.setDepartureCity(cityDeparture);
        City cityArrival = cityService.findById(flightDTO.getIdArrivalCity());
        flight.setArrivalCity(cityArrival);
        flight.setStatus(flightDTO.getStatus());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setIdFlight(flightDTO.getIdFlight());
        flight = this.flightService.update(flight);
        return flight;
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemFlight> findById(@PathVariable("id") Integer idFlight){
        boolean success;
        String message;
        IItemFlight itemFlight;
        itemFlight = new IItemFlight();
        Flight flight = flightService.findById(idFlight);

        if(flight==null){
            success = false;
            message = "No flights found";
        }else{
            itemFlight = showFlight(flight, itemFlight);
            success = true;
            message = "Flights found";
        }
        return new ResponseApi<>(success, message, itemFlight);
    }

    public IItemFlight showFlight(Flight flight, IItemFlight itemFlight){
        itemFlight.setIdFlight(flight.getIdFlight());
        itemFlight.setModel(flight.getAirplane().getModel());
        itemFlight.setAirline(flight.getAirline().getAirlineName());
        itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());
        itemFlight.setIdDepartureCity(flight.getDepartureCity().getIdCity().toString());
        itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());
        itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());
        itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());
        itemFlight.setDepartureDate(MyFormatDate.splitDate(flight.getDepartureTime()));
        itemFlight.setDepartureTime(MyFormatDate.splitTime(flight.getDepartureTime()));
        itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());
        itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());
        itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());
        itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());
        itemFlight.setArrivalDate(MyFormatDate.splitDate(flight.getArrivalTime()));
        itemFlight.setArrivalTime(MyFormatDate.splitTime(flight.getArrivalTime()));
        itemFlight.setStatus(flight.getStatus());
        return itemFlight;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idFlight){
        flightService.delete(idFlight);
    }
}
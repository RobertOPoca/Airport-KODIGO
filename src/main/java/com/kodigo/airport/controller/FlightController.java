package com.kodigo.airport.controller;

import com.kodigo.airport.dto.FlightDTO;
import com.kodigo.airport.item.IItemFlight;
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
        boolean success = false;
        String message = "No Flights found";
        List<IItemFlight> itemFlightList = new ArrayList<>();
        List<Flight> flightList = flightService.findAll();
        if(!flightList.isEmpty()){
            success = true;
            message = "flights found";
            itemFlightList = showAllFlights(flightList, itemFlightList);
        }
        return new ResponseApi<>(success, message, itemFlightList);
    }
    public  List<IItemFlight> showAllFlights(List<Flight> flightList, List<IItemFlight> itemFlightList ){
        for(Flight flight: flightList){
            IItemFlight itemFlight = new IItemFlight();
            itemFlight = showFlight(flight, itemFlight);
            itemFlightList.add(itemFlight);
        }
        return itemFlightList;
    }

    @PostMapping
    public ResponseApi<IItemFlight> create(@RequestBody FlightDTO flightDTO){
        boolean success = false;
        String message = "Error";
        Flight flight = new Flight();
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
        flight.setAirplane(airplaneService.findById(flightDTO.getModel()));
        flight.setAirline(airlineService.findById(flightDTO.getIdAirline()));
        flight.setDepartureCity(cityService.findById(flightDTO.getIdDepartureCity()));
        flight.setArrivalCity(cityService.findById(flightDTO.getIdArrivalCity()));
        flight.setStatus(flightDTO.getStatus());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight = this.flightService.create(flight);
        return flight;
    }

    @PutMapping
    public ResponseApi<IItemFlight>  update(@RequestBody FlightDTO flightDTO) {
        boolean success = false;
        String message = "Error";
        Flight flight = new Flight();
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
        flight.setAirplane(airplaneService.findById(flightDTO.getModel()));
        flight.setAirline(airlineService.findById(flightDTO.getIdAirline()));
        flight.setDepartureCity(cityService.findById(flightDTO.getIdDepartureCity()));
        flight.setArrivalCity(cityService.findById(flightDTO.getIdArrivalCity()));
        flight.setStatus(flightDTO.getStatus());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setIdFlight(flightDTO.getIdFlight());
        flight = this.flightService.update(flight);
        return flight;
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemFlight> findById(@PathVariable("id") Integer idFlight){
        boolean success = false;
        String message = "No flight found";
        IItemFlight itemFlight = new IItemFlight();
        Flight flight = flightService.findById(idFlight);
        if(flight!=null){
            itemFlight = showFlight(flight, itemFlight);
            success = true;
            message = "Flight found";
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
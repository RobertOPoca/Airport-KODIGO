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
            for(Flight flight: flightList){
                IItemFlight itemFlight = new IItemFlight();

                itemFlight.setIdFlight(flight.getIdFlight());
                itemFlight.setModel(flight.getAirplane().getModel());

                itemFlight.setAirline(flight.getAirline().getAirlineName());
                itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


                itemFlight.setIdDepartureCity(flight.getDepartureCity().getIdCity().toString());//idCity
                itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
                itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
               itemFlight.setDepartureDate(MyFormatDate.splitDate(flight.getDepartureTime())); //date
                itemFlight.setDepartureTime(MyFormatDate.splitTime(flight.getDepartureTime())); //time

                itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
                itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
                itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
               itemFlight.setArrivalDate(MyFormatDate.splitDate(flight.getArrivalTime()));//date
                itemFlight.setArrivalTime(MyFormatDate.splitTime(flight.getArrivalTime()));//time

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
        IItemFlight itemFlight = new IItemFlight();
        try{

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


            if(flight!=null){
                itemFlight.setIdFlight(flight.getIdFlight());
                itemFlight.setModel(flight.getAirplane().getModel());

                itemFlight.setAirline(flight.getAirline().getAirlineName());
                itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


                itemFlight.setIdDepartureCity(flight.getDepartureCity().getIdCity().toString());//idCity
                itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
                itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
                itemFlight.setDepartureDate(MyFormatDate.splitDate(flight.getDepartureTime())); //date
                itemFlight.setDepartureTime(MyFormatDate.splitTime(flight.getDepartureTime())); //time

                itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
                itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
                itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
                itemFlight.setArrivalDate(MyFormatDate.splitDate(flight.getArrivalTime()));//date
                itemFlight.setArrivalTime(MyFormatDate.splitTime(flight.getArrivalTime()));//time

                itemFlight.setStatus(flight.getStatus());

                success = true;
                message = "Airline was created successfully";
            }else {

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
        boolean success;
        success = false;
        String message;
        message = "";
        Flight flight;
        flight = new Flight();
        IItemFlight itemFlight = new IItemFlight();
        try{

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
            if(flight!=null){
                itemFlight.setIdFlight(flight.getIdFlight());
                itemFlight.setModel(flight.getAirplane().getModel());

                itemFlight.setAirline(flight.getAirline().getAirlineName());
                itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


                itemFlight.setIdDepartureCity(flight.getDepartureCity().getIdCity().toString());//idCity
                itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
                itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
                itemFlight.setDepartureDate(MyFormatDate.splitDate(flight.getDepartureTime())); //date
                itemFlight.setDepartureTime(MyFormatDate.splitTime(flight.getDepartureTime())); //time

                itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
                itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
                itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
                itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
                itemFlight.setArrivalDate(MyFormatDate.splitDate(flight.getArrivalTime()));//date
                itemFlight.setArrivalTime(MyFormatDate.splitTime(flight.getArrivalTime()));//time

                itemFlight.setStatus(flight.getStatus());
                success = true;
                message = "Flight was updated successfully";
            }else {
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
        IItemFlight itemFlight;
        itemFlight = new IItemFlight();
        Flight flight = flightService.findById(idFlight);

        if(flight==null){
            success = false;
            message = "No flights found";
        }else{
            success = true;
            message = "Flights found";

            itemFlight.setIdFlight(flight.getIdFlight());
            itemFlight.setModel(flight.getAirplane().getModel());

            itemFlight.setAirline(flight.getAirline().getAirlineName());
            itemFlight.setIdAirline(flight.getAirline().getIdAirline().toString());


            itemFlight.setIdDepartureCity(flight.getDepartureCity().getIdCity().toString());//idCity
            itemFlight.setDepartureCity(flight.getDepartureCity().getCityName());//DepartureCity
            itemFlight.setIdDepartureCountry(flight.getDepartureCity().getCountry().getIdCountry().toString());//idCountry
            itemFlight.setDepartureCountry(flight.getDepartureCity().getCountry().getCountryName());//DepartureCountry
            itemFlight.setDepartureDate(MyFormatDate.splitDate(flight.getDepartureTime())); //date
            itemFlight.setDepartureTime(MyFormatDate.splitTime(flight.getDepartureTime())); //time

            itemFlight.setIdDestinationCity(flight.getArrivalCity().getIdCity().toString());//idCity
            itemFlight.setDestinationCity(flight.getArrivalCity().getCityName());//ArrivalCity
            itemFlight.setIdArrivalCountry(flight.getArrivalCity().getCountry().getIdCountry().toString());//idCountry
            itemFlight.setArrivalCountry(flight.getArrivalCity().getCountry().getCountryName());//ArrivalCountry
            itemFlight.setArrivalDate(MyFormatDate.splitDate(flight.getArrivalTime()));//date
            itemFlight.setArrivalTime(MyFormatDate.splitTime(flight.getArrivalTime()));//time

            itemFlight.setStatus(flight.getStatus());
        }
        return new ResponseApi<>(success, message, itemFlight);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idFlight){
        flightService.delete(idFlight);
    }
}

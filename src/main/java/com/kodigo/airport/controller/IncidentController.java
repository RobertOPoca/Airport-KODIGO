package com.kodigo.airport.controller;

import com.kodigo.airport.dto.IncidentDTO;
import com.kodigo.airport.item.IItemIncident;
import com.kodigo.airport.service.FlightService;
import com.kodigo.airport.service.IncidentService;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.model.Incident;
import com.kodigo.airport.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public ResponseApi<List<IItemIncident>> getAllIncidents(){
        boolean success;
        String message;
        List<IItemIncident> itemIncidentList = new ArrayList<>();
        List<Incident> incidentList = incidentService.findAll();
        if(incidentList.isEmpty()){
            success = false;
            message = "No incidents found";
        }else{
            success = true;
            message = "Incidents found";
            for(Incident incident: incidentList){
                var itemIncident = new IItemIncident();

                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
                itemIncident.setIdFlight(incident.getFlight().toString());
                itemIncident.setDate(String.valueOf(incident.getDateTime()));
                itemIncident.setTime(String.valueOf(incident.getDateTime().getTime()));
                itemIncidentList.add(itemIncident);
            }
        }
        return new ResponseApi<>(success, message, itemIncidentList);
    }

    @PostMapping
    public ResponseApi<IItemIncident> create(@RequestBody IncidentDTO incidentDTO){
        boolean success = false;
        String message = "";
        Incident incident = new Incident();
        IItemIncident itemIncident = new IItemIncident();
        try{

            Flight flight = flightService.findById(incidentDTO.getIdFlight());
            incident.setIdFlight(flight);
            incident.setFlight(flight.getIdFlight());
            incident.setDateTime(new Date());

            incident = incidentService.create(incident);

            if(incident!=null){
                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
                itemIncident.setDate(String.valueOf(incident.getDateTime()));
                itemIncident.setTime(String.valueOf(incident.getDateTime().getTime()));

                success = true;
                message = "Incident was create successfully";
            }else{
                message = "Error";
            }
        }catch(Exception e){
            message = e.getMessage();
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @PutMapping
    public ResponseApi<IItemIncident> update(@RequestBody IncidentDTO incidentDTO){
        boolean success = false;
        String message = "";
        Incident incident = new Incident();
        IItemIncident itemIncident = new IItemIncident();
        try{

            Flight flight = flightService.findById(incidentDTO.getIdFlight());
            incident.setIdFlight(flight);
            incident.setFlight(flight.getIdFlight());
            incident.setDateTime(new Date());
            incident.setIdIncident(incidentDTO.getIdIncident());

            incident = incidentService.update(incident);

            if(incident!=null){
                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
                itemIncident.setDate(String.valueOf(incident.getDateTime()));
                itemIncident.setTime(String.valueOf(incident.getDateTime().getTime()));

                success = true;
                message = "Incident was update successfully";
            }else{
                message = "Error";
            }
        }catch(Exception e){
            message = e.getMessage();
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemIncident> findById(@PathVariable("id") Integer idIncident){
        boolean success;
        String message;
        IItemIncident itemIncident = new IItemIncident();
        Incident incident = incidentService.findById(idIncident);
        if(incident==null){
            success = false;
            message = "No incident found";
        }else{
            success = true;
            message = "Incident found";
            itemIncident.setIdIncident(incident.getIdIncident());
            itemIncident.setDescription(incident.getDescription());
            itemIncident.setFlight(incident.getIdFlight().getIdFlight().toString());
            itemIncident.setIdFlight(incident.getFlight().toString());
            itemIncident.setDate(String.valueOf(incident.getDateTime()));
            itemIncident.setTime(String.valueOf(incident.getDateTime().getTime()));
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idIncident){
        incidentService.delete(idIncident);
    }
}

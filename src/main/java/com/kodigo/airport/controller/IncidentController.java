package com.kodigo.airport.controller;

import com.kodigo.airport.dto.IncidentDTO;
import com.kodigo.airport.item.IItemIncident;
import com.kodigo.airport.service.FlightService;
import com.kodigo.airport.service.IncidentService;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.model.Incident;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.utils.MyFormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private IncidentService incidentService;

    private IItemIncident getIItemIncident(Incident incident){
        IItemIncident itemIncident = new IItemIncident();
        itemIncident.setIdIncident(incident.getIdIncident());
        itemIncident.setDescription(incident.getDescription());
        itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
        itemIncident.setDate(MyFormatDate.splitDate(incident.getDateTime()));
        itemIncident.setTime(MyFormatDate.splitTime(incident.getDateTime()));

        return itemIncident;
    }


    @GetMapping
    public ResponseApi<List<IItemIncident>> getAllIncidents(){

        List<IItemIncident> itemIncidentList = new ArrayList<>();
        List<Incident> incidentList = incidentService.findAll();
        String message = "No incidents found";
        boolean success = false;

        if(!incidentList.isEmpty()){
            success = true;
            message = "Incidents found";
            for(Incident incident: incidentList){
                itemIncidentList.add(getIItemIncident(incident));
            }
        }
        return new ResponseApi<>(success, message, itemIncidentList);
    }

    @PostMapping
    public ResponseApi<IItemIncident> create(@RequestBody IncidentDTO incidentDTO){

        IItemIncident itemIncident = new IItemIncident();
        Incident incident = new Incident();
        String message = "Error";
        boolean success = false;

        try{

            Flight flight = flightService.findById(incidentDTO.getIdFlight());
            incident.setFlight(flight);
            incident.setDateTime(incidentDTO.getDateTime());
            incident.setDescription(incidentDTO.getDescription());
            incident = incidentService.create(incident);

            if(incident!=null){
                itemIncident = getIItemIncident(incident);
                message = "Incident was create successfully";
                success = true;
            }
        }catch(Exception e){
            message = e.getMessage();
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @PutMapping
    public ResponseApi<IItemIncident> update(@RequestBody IncidentDTO incidentDTO){

        IItemIncident itemIncident = new IItemIncident();
        Incident incident = new Incident();
        boolean success = false;
        String message = "Error";

        try{
            Flight flight = flightService.findById(incidentDTO.getIdFlight());
            incident.setFlight(flight);
            incident.setDateTime(incidentDTO.getDateTime());
            incident.setIdIncident(incidentDTO.getIdIncident());
            incident.setDescription(incidentDTO.getDescription());
            incident = incidentService.update(incident);

            if(incident!=null){
                itemIncident = getIItemIncident(incident);
                message = "Incident was update successfully";
                success = true;
            }
        }catch(Exception e){
            message = e.getMessage();
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @GetMapping("/{id}")
    public ResponseApi<IItemIncident> findById(@PathVariable("id") Integer idIncident){

        Incident incident = incidentService.findById(idIncident);
        IItemIncident itemIncident = new IItemIncident();
        boolean success = false;
        String message = "No incident found";

        if(incident!=null){
            success = true;
            message = "Incident found";
            itemIncident = getIItemIncident(incident);
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idIncident){
        incidentService.delete(idIncident);
    }

    @GetMapping("/flight/{id}")
    public ResponseApi<List<IItemIncident>> getAllIncidentsByFlight(@PathVariable("id") Integer id){

        List<IItemIncident> itemIncidentList = new ArrayList<>();
        List<Incident> incidentList = incidentService.findAll();
        String message = "No incidents found";
        boolean success = false;

        if(!incidentList.isEmpty()){
            for(Incident incident: incidentList){
                if(incident.getFlight().getIdFlight().equals(id)){
                    itemIncidentList.add(getIItemIncident(incident));
                }
            }
            message = "Incidents found";
            success = true;
        }
        return new ResponseApi<>(success, message, itemIncidentList);
    }
}

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
                IItemIncident itemIncident = new IItemIncident();

                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
                itemIncident.setDate(new MyFormatDate().splitDate(incident.getDateTime()));
                itemIncident.setTime(new MyFormatDate().splitTime(incident.getDateTime()));
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
            incident.setFlight(flight);

            incident.setDateTime(incidentDTO.getDateTime());
            incident.setDescription(incidentDTO.getDescription());
            incident = incidentService.create(incident);

            if(incident!=null){
                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setDate(new MyFormatDate().splitDate(incident.getDateTime()));
                itemIncident.setTime(new MyFormatDate().splitTime(incident.getDateTime()));

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
            incident.setFlight(flight);
            incident.setDateTime(incidentDTO.getDateTime());
            incident.setIdIncident(incidentDTO.getIdIncident());
            incident.setDescription(incidentDTO.getDescription());

            incident = incidentService.update(incident);

            if(incident!=null){
                itemIncident.setIdIncident(incident.getIdIncident());
                itemIncident.setDescription(incident.getDescription());
                itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
                itemIncident.setDate(new MyFormatDate().splitDate(incident.getDateTime()));
                itemIncident.setTime(new MyFormatDate().splitTime(incident.getDateTime()));

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
            itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
            itemIncident.setDate(new MyFormatDate().splitDate(incident.getDateTime()));
            itemIncident.setTime(new MyFormatDate().splitTime(incident.getDateTime()));
        }
        return new ResponseApi<>(success, message, itemIncident);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idIncident){
        incidentService.delete(idIncident);
    }

    @GetMapping("/flight/{id}")
    public ResponseApi<List<IItemIncident>> getAllIncidentsByFlight(@PathVariable("id") Integer id){
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
                if(incident.getFlight().getIdFlight()==id){
                    IItemIncident itemIncident = new IItemIncident();
                    itemIncident.setIdIncident(incident.getIdIncident());
                    itemIncident.setDescription(incident.getDescription());
                    itemIncident.setFlight(incident.getFlight().getIdFlight().toString());
                    itemIncident.setDate(new MyFormatDate("yyyy-mm-dd").splitDate(incident.getDateTime()));
                    itemIncident.setTime(new MyFormatDate("hh:mm:ss").splitTime(incident.getDateTime()));
                    itemIncidentList.add(itemIncident);
                }
            }
        }
        return new ResponseApi<>(success, message, itemIncidentList);
    }
}

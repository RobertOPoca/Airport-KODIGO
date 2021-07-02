package com.kodigo.airport.service;

import com.kodigo.airport.model.Flight;
import com.kodigo.airport.repository.IFlightRepository;
import com.kodigo.airport.repository.IIncidentRepository;
import com.kodigo.airport.utils.ExcelReport;
import com.kodigo.airport.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ExcelReportService {
    @Autowired
    private FlightService flightService;
    @Autowired
    private IncidentService incidentService;

    public boolean generateReport(String date, String weather ){

        List<Flight> flightList =  flightService.getDateBetweenDate(date+" 00:00:00", date+" 23:59:59");
        List<Incident> incidentList = incidentService.findAll();

        return new ExcelReport(weather, flightList,incidentList).write();
    }

    public boolean generateReport(int id, String weather){

        List<Incident> incidentList = incidentService.findAll();
        Flight flight = flightService.findById(id);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);

        return new ExcelReport(weather, flightList,incidentList).write();
    }
}

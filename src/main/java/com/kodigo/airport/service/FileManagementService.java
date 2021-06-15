package com.kodigo.airport.service;

import com.kodigo.airport.dto.BodyReportDTO;
import com.kodigo.airport.model.Flight;
import com.kodigo.airport.repository.IFlightRepository;
import com.kodigo.airport.repository.IIncidentRepository;
import com.kodigo.airport.utils.ExcelBatch;
import com.kodigo.airport.utils.ExcelReport;
import com.kodigo.airport.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FileManagementService {
    @Autowired
    private ExcelBatch excelBatch = new ExcelBatch();
    @Autowired
    private ExcelReport excelReport;
    @Autowired
    private IFlightRepository flightRepo;
    @Autowired
    private IIncidentRepository incidentRepo;

    public boolean readFile(){
        return this.excelBatch.read();
    }

    public boolean excelReportByDate(BodyReportDTO bodyReport){

        String start = bodyReport.getDateOrId()+" 00:00:00";
        String end = bodyReport.getDateOrId()+" 23:59:59";

        List<Flight> flightList =  flightRepo.getDateBetweenDate(start, end);
        List<Incident> incidentList = incidentRepo.findAll();

        return this.excelReport.write(incidentList, flightList, bodyReport.getWeather());
    }

    public boolean excelReportById(BodyReportDTO bodyReport){

        int  id = Integer.parseInt(bodyReport.getDateOrId());

        Optional<Flight> flightOptional = flightRepo.findById(id);
        Flight flight = flightOptional.orElse(null);

        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);

        List<Incident> incidentList = incidentRepo.findAll();

        return this.excelReport.write(incidentList, flightList, bodyReport.getWeather());
    }
}

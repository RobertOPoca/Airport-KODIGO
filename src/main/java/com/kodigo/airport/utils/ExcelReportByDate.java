package com.kodigo.airport.utils;

import com.kodigo.airport.model.Flight;
import com.kodigo.airport.model.Incident;

import java.util.List;

public class ExcelReportByDate implements IFileWrite{
    @Override
    public void write(List<Incident> incidents, String weather) {
        String path = incidents.get(0).getIdFlight().getDepartureTime().toString();
        path += ".xlsx";
    }
}

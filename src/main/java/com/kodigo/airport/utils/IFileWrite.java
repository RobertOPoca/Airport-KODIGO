package com.kodigo.airport.utils;

import com.kodigo.airport.model.Flight;
import com.kodigo.airport.model.Incident;

import java.util.List;

public interface IFileWrite {
    void write(List<Incident> incidents, String weather);
}

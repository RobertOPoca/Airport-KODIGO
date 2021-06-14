package com.kodigo.airport.utils;

import com.kodigo.airport.model.Incident;

import java.util.List;

public interface IFileWrite {
    boolean write(List<Incident> incidents, String weather);
}

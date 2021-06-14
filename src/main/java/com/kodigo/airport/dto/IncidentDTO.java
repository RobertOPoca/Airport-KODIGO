package com.kodigo.airport.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class IncidentDTO {
    private Integer idIncident;
    private String description;
    private Integer idFlight;
    private String dateTime;
}

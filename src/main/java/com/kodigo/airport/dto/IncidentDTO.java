package com.kodigo.airport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentDTO {
    private Integer idIncident;
    private String description;
    private Integer idFlight;
    private String dateTime;
}

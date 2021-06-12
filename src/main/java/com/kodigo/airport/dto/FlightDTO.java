package com.kodigo.airport.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightDTO {
    private Integer idFlight;
    private String model;
    private String airline;
    private String departureCity;
    private String departureCountry;
    private String destinationCity;
    private String destinationCountry;
    private String status;
    private String departureTime;
    private String arrivalTime;
}

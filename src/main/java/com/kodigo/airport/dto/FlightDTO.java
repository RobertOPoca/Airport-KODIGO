package com.kodigo.airport.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightDTO {
    private Integer idFlight;
    private String model;
    private Integer idAirline;
    private Integer idDepartureCity;
    private Integer idArrivalCity;
    private String status;
    private String departureTime;
    private String arrivalTime;
}

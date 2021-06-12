package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IItemFlight implements IItem {
    Integer idFlight;
    String model;

    String airline;
    String idAirline;

    String departureCity;
    String idDepartureCity;

    String departureCountry;
    String idDepartureCountry;

    String destinationCity;
    String idDestinationCity;

    String arrivalCountry;
    String idArrivalCountry;

    String status;
    String departureTime;
    String arrivalTime;
}

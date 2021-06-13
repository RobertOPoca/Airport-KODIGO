package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IItemFlight implements IItem {
    Integer idFlight;
    String model;

    String idAirline;
    String airline;

    String idDepartureCity;
    String departureCity;

    String idDepartureCountry;
    String departureCountry;

    String idDestinationCity;
    String destinationCity;

    String idArrivalCountry;
    String arrivalCountry;

    String departureDate;
    String departureTime;

    String arrivalDate;
    String arrivalTime;

    String status;

}
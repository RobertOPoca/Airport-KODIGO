package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "flight")
public class Flight {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_flight")
    private Integer idFlight;

    @Column(name = "model")
    private String model;

    @Column(name = "id_airline")
    private Integer idAirline;

    @Column(name = "id_departure_city")
    private Integer idDepartureCity;

    @Column(name = "id_destination_city")
    private Integer idDestinationCity;

    @Column(name="status", columnDefinition="enum('ONTIME','DELAYED','CANCELLED'")
    public String status;

    @Column(name = "departure_time")
    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.DATE)
    private Date arrivalTime;







}

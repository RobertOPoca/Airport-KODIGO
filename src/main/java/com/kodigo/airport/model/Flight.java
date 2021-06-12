package com.kodigo.airport.model;

import com.kodigo.airport.model.Airline;
import com.kodigo.airport.model.City;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
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
    @Column(name = "id_flight")
    private Integer idFlight;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "id_airline", nullable = false)
    private Integer idAirline;

    @Column(name = "id_departure_city", nullable = false)
    private Integer idDepartureCity;

    @Column(name = "id_destination_city", nullable = false)
    private Integer idArrivalCity;

    @Column(name="status", columnDefinition="enum('ONTIME','DELAYED','CANCELLED'")
    public String status;

    @Column(name = "departure_time")
    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.DATE)
    private Date arrivalTime;


    @ManyToOne
    @JoinColumn(name="id_airline", insertable = false, updatable = false, nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name="id_departure_city", insertable = false, updatable = false, nullable = false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name="id_destination_city", insertable = false, updatable = false, nullable = false)
    private City arrivalCity;
}

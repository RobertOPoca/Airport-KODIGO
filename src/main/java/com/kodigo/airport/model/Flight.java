package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



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

    //@Column(name = "model", nullable = false, length = 50)
    //private Airplane model;

    //@Column(name = "id_airline", nullable = false)
    //private Airline idAirline;

    //@Column(name = "id_departure_city", nullable = false)
    //private City idDepartureCity;

    //@Column(name = "id_destination_city", nullable = false)
    //private City idArrivalCity;

    @Column(name="status", columnDefinition="enum('ONTIME','DELAYED','CANCELLED'")
    public String status;

    @Column(name = "departure_time")
    //@Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Column(name = "arrival_time")
    //@Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;


    @ManyToOne
    @JoinColumn(name="id_airline")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name="model")
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name="id_departure_city")
    private City departureCity;

    @ManyToOne
    @JoinColumn(name="id_destination_city")
    private City arrivalCity;
}

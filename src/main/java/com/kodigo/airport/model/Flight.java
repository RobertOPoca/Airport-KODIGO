package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

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

    @Column(name="status", columnDefinition="enum('ONTIME','DELAYED','CANCELLED'")
    public String status;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

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

package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name="id_airline_plane")
    private AirlineAirplane airlineAirplane;


    @ManyToOne
    @JoinColumn(name="id_source_city")
    private City city;
}

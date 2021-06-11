package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "airline")
public class Airline {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airline")
    private Integer idAirline;
    @Column(name = "airline")
    private String airlineName;
}

package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "airline")
public class Airline {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airline", nullable = false)
    private Integer idAirline;


    @Column(name = "airline", nullable = false, unique = true, length = 50)
    private String airlineName;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id_airline")
    private List<Airline> airlineList;
}

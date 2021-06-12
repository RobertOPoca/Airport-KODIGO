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


    @ManyToOne
    @JoinColumn(name="id_airline", insertable = false, updatable = false, nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name="id_departure_city", insertable = false, updatable = false, nullable = false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name="id_destination_city", insertable = false, updatable = false, nullable = false)
    private City destinationCity;
    /*@ManyToOne
    @JoinColumn(name="id_destination_city", referencedColumnName="id_city", insertable=false, updatable=false)
    private City destinationCity;

    @ManyToOne
    @JoinColumn(name="id_departure_city", referencedColumnName="id_city", insertable=false, updatable=false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name="model", referencedColumnName="model", insertable=false, updatable=false)
    private Airplane airplane;


    @ManyToOne
    @JoinColumn(name="id_airline", referencedColumnName="id_airline", insertable=false, updatable=false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name="id_flight", referencedColumnName="id_incident", insertable=false, updatable=false)
    private Incident incident;*/

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id_source_city")
    private List<Flight> flightList;

}

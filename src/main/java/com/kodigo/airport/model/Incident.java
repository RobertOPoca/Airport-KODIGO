package com.kodigo.airport.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "incident")
public class Incident {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident")
    private Integer idIncident;

    @Column(name = "description")
    private String description;

    @Column(name = "date_time")
    //@Temporal(TemporalType.TIMESTAMP)
    private String dateTime;

    @ManyToOne
    @JoinColumn(name="id_flight")
    private Flight flight;
}

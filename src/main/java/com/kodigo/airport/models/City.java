package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "city")
public class City {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Integer idCity;

    @Column(name = "city")
    private String cityName;

    @ManyToOne
    @JoinColumn(name="Id_Country")
    private Country country;


    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id_source_city")
    private List<Flight> flightList;

}

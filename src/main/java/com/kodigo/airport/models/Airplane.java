package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @Column(name = "model")
    private String model;
    @Column(name = "reach")
    private float reach;


    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="model")
    private List<CommercialAirplane> commercialAirplaneList;
}

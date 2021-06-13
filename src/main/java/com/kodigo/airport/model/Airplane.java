package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "airplane")
public class Airplane {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model")
    private String model;
    @Column(name = "reach")
    private float reach;


    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="model")
    private List<CommercialAirplane> commercialAirplaneList;
}

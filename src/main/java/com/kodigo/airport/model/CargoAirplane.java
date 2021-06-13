package com.kodigo.airport.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "cargo_airplane")
public class CargoAirplane {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Integer idCommercial;

    @Column(name = "model")
    private String model;

    @Column(name = "weight")
    private float weight;

    @Column(name = "volume")
    private float volume;

    @ManyToOne(optional = false)
    @JoinColumn(name="model", referencedColumnName="model", insertable=false, updatable=false)
    private Airplane airplane;

}
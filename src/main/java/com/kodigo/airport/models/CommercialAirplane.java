package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "commercial_airplane")
public class CommercialAirplane{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commercial")
    private Integer idCommercial;

    @Column(name = "passenger")
    private Integer passenger;

    @ManyToOne
    @JoinColumn(name="model")
    private Airplane airplane;
}

package com.kodigo.airport.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "country")
public class Country {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Integer idCountry;

    @Column(name = "country")
    private String countryName;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id_country")
    private List<City> cityList;
}

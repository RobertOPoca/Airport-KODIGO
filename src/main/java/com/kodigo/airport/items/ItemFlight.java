package com.kodigo.airport.items;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemFlight implements Item{
    private Integer id;
    private String airline;
    private String airplane;
    private String sourceCity;
    private String sourceCountry;
}

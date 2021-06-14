package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IItemCountry implements IItem{
    Integer idCountry;
    String countryName;
}

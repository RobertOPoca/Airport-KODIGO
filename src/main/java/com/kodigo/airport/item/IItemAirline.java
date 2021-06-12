package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IItemAirline implements IItem {
    Integer idAirline;
    String airlineName;
}

package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IItemIncident implements IItem{
    Integer idIncident;
    String description;
    String idFlight;
    String flight;
    String date;
    String time;
}

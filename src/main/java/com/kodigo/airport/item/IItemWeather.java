package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IItemWeather implements IItem{
    String main;
    String description;
}

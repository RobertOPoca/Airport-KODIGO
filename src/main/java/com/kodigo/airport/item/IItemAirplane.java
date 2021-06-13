package com.kodigo.airport.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IItemAirplane implements IItem{
    String model;
    Float reach;
}

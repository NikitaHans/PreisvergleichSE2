package edu.hm.shareit.models;

public class CarAttribute {
    private String name;
    private ClimateZone zone;

    public CarAttribute(String name, ClimateZone climateZone){
        this.name = name;
        this.zone = climateZone;
    }
}

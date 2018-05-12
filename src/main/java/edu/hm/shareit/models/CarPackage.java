package edu.hm.shareit.models;

public class CarPackage {

    private String name;
    private CarAttribute[] inclAttributes;

    public CarPackage(String name, CarAttribute[] attributes){
        this.name = name;
        this.inclAttributes = attributes;
    }
}

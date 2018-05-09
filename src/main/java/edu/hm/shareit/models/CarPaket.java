package edu.hm.shareit.models;

public class CarPaket {

    private String name;
    private Attribute[] inclAttributes;

    public CarPaket(String name, Attribute[] attributes){
        this.name = name;
        this.inclAttributes = attributes;
    }
}

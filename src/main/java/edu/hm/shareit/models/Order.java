package edu.hm.shareit.models;

import java.util.ArrayList;

public class Order {
    private String name;

    private Brand brand;
    private CarPackage packet;
    private CarAttribute[] attributes;

    private Order(String name, Brand brand, CarPackage packet) {
        this.name = name;
        this.brand = brand;
        this.packet = packet;
    }

    Order(String name, Brand brand, CarPackage packet, ArrayList<CarAttribute> attributes){
        this(name, brand, packet);
        CarAttribute[] array = new CarAttribute[attributes.size()];
        this.attributes = attributes.toArray(array);
    }

    Order(String name, Brand brand, CarPackage packet, CarAttribute[] attributes){
        this(name, brand, packet);
        this.attributes = attributes;
    }


    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public CarPackage getPacket() {
        return packet;
    }

    public CarAttribute[] getAttributes() {
        return attributes;
    }


}

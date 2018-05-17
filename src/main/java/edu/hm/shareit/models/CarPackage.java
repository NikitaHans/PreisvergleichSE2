package edu.hm.shareit.models;

import java.util.ArrayList;
import java.util.Collections;

public class CarPackage {

    private String name;
    private ArrayList<CarAttribute> attributes;

    public CarPackage(String name){
        this.name = name;
        attributes = new ArrayList<>();
    }

    public CarPackage(String name, CarAttribute[] attributes){
        this(name);
        Collections.addAll(this.attributes, attributes);
    }

    public String getName() {
        return name;
    }

    public CarPackage addAttributes(CarAttribute... attributes) {
        Collections.addAll(this.attributes, attributes);
        return this;
    }

    public CarPackage removeAttributes(CarAttribute... attributes) {
        for( CarAttribute attribute : attributes) {
            this.attributes.remove(attribute);
        }
        return this;
    }

    public CarAttribute[] getAttributes() {
        CarAttribute[] array = new CarAttribute[attributes.size()];
        return attributes.toArray(array);
    }
}

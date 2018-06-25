package edu.hm.shareit.models;

import java.io.Serializable;

public class CompositeKey implements Serializable {
    private String brand;
    private String modelName;

    public CompositeKey (){}

    public CompositeKey (String brand, String modelName){
        this.brand = brand;
        this.modelName = modelName;
    }

}

package edu.hm.shareit.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* Very simple sample class to demonstrate the usage of the framework.
*
* */

@Entity
@Table(name = "TCar")
public class Car {

    private String brand;
    private String modelName;

    /**
     * Empty constructor for framework.
     */
    public Car() {
    }

    /**
     * Constructor.
     * @param brand
     * @param modelName
     */

    public Car(String brand, String modelName) {
        this.brand = brand;
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }


    public String getModelName() {
        return modelName;
    }
}

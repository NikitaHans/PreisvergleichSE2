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

    @Id private String serial;
    private String brand;
    private String modelName;

    /**
     * Empty constructor for framework.
     */
    public Car() {
    }

    /**
     * Constructor.
     * @param serial
     * @param brand
     * @param modelName
     */

    public Car(String serial, String brand, String modelName) {
        this.serial = serial;
        this.brand = brand;
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public String getSerial() {
        return serial;
    }

    public String getModelName() {
        return modelName;
    }
}

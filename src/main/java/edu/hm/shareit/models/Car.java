package edu.hm.shareit.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Objects;

/*
* Very simple sample class to demonstrate the usage of the framework.
*
* */

@Entity
@Table(name = "TCar")
@IdClass(CompositeKey.class)
public class Car {

    @Id private String brand;
    @Id private String modelName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) &&
                Objects.equals(modelName, car.modelName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(brand, modelName);
    }
}

package edu.hm.shareit.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TAttribute")
public class CarAttribute {
    @Id private String name;
    @ManyToOne
    @JoinColumn(name="zone")
    private ClimateZone zone;
    private float singlePrice;


    public CarAttribute(){}



    public CarAttribute(String name, ClimateZone climateZone, float singlePrice){
        this.name = name;
        this.zone = climateZone;
        this.singlePrice = singlePrice;

    }

    public CarAttribute (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ClimateZone getZone() {
        return zone;
    }

    public float getSinglePrice() {
        return singlePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarAttribute that = (CarAttribute) o;
        return Float.compare(that.singlePrice, singlePrice) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(zone, that.zone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, zone, singlePrice);
    }
}

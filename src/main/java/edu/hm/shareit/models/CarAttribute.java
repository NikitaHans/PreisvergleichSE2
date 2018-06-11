package edu.hm.shareit.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TAttribute")
public class CarAttribute {
    @Id private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClimateZone> zones;
    private float singlePrice;


    public CarAttribute(){}



    public CarAttribute(String name, List<ClimateZone> climateZone, float singlePrice){
        this.name = name;
        this.zones = climateZone;
        this.singlePrice = singlePrice;

    }

    public String getName() {
        return name;
    }

    public List<ClimateZone> getZones() {
        return zones;
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
                Objects.equals(zones, that.zones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, zones, singlePrice);
    }
}

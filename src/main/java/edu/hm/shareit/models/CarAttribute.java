package edu.hm.shareit.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TAttribute")
public class CarAttribute {
    @Id private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ClimateZone> zones;


    public CarAttribute(){}

    public CarAttribute(String name, List<ClimateZone> climateZone){
        this.name = name;
        this.zones = climateZone;
    }

    public String getName() {
        return name;
    }

    public List<ClimateZone> getZones() {
        return zones;
    }
}

package edu.hm.shareit.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TNation")
public class Nation {
    @Id
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private ClimateZone climateZone;

    public Nation(){}

    public Nation(String name, ClimateZone zone){
        this.name = name;
        this.climateZone = zone;
    }

    public String getName() {
        return name;
    }

    public ClimateZone getClimateZone() {
        return climateZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nation nation = (Nation) o;
        return Objects.equals(name, nation.name) &&
                Objects.equals(climateZone, nation.climateZone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, climateZone);
    }
}

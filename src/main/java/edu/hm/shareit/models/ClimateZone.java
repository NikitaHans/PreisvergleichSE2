package edu.hm.shareit.models;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "TClimateZone")
public class ClimateZone {
    @Id
    private String zone;

    public ClimateZone(){}

    public ClimateZone(String zone){
        this.zone = zone;
    }

    public String getZone(){
        return this.zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClimateZone that = (ClimateZone) o;
        return Objects.equals(zone, that.zone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(zone);
    }
}

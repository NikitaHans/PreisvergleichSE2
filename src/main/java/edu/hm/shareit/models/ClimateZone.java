package edu.hm.shareit.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TZone")
public class ClimateZone {

   // public static final String HOT = "hot";
   // public static final String NORMAL = "normal";
   // public static final String COLD = "cold";


    @Id private String zone;

    public ClimateZone(){}

    public ClimateZone(String zone){
        this.zone = zone;
    }

    public String getZone(){
        return this.zone;
    }


}

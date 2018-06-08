package edu.hm.shareit.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "TPackage")
public class CarPackage {

    @Id private String name;
    @ManyToMany
    private List<CarAttribute> attributes;

    public CarPackage (){}

    public CarPackage(String name){
        this.name = name;
        attributes = new ArrayList<>();
    }

    public CarPackage(String name, CarAttribute[] attributes){
        this(name);
        Collections.addAll(this.attributes, attributes);
    }

    public String getName() {
        return name;
    }

    public CarPackage addAttributes(CarAttribute... attributes) {
        Collections.addAll(this.attributes, attributes);
        return this;
    }

    public CarPackage removeAttributes(CarAttribute... attributes) {
        for( CarAttribute attribute : attributes) {
            this.attributes.remove(attribute);
        }
        return this;
    }

    public CarAttribute[] getAttributes() {
        CarAttribute[] array = new CarAttribute[attributes.size()];
        return attributes.toArray(array);
    }
}

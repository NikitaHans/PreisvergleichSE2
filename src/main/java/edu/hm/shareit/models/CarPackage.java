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
    private float packagePrice;

    public CarPackage (){}

    public CarPackage(String name){
        this.name = name;
        attributes = new ArrayList<>();
        packagePrice = 0;
    }

    public CarPackage(String name, CarAttribute[] attributes){
        this(name);
        Collections.addAll(this.attributes, attributes);
        for(CarAttribute attribute : attributes){
            this.packagePrice += attribute.getSinglePrice() * 0.1;
        }
    }

    public String getName() {
        return name;
    }

    public CarPackage addAttributes(CarAttribute... attributes) {
        Collections.addAll(this.attributes, attributes);

        for(CarAttribute attribute : attributes){
            this.packagePrice += attribute.getSinglePrice() * 0.1;
        }

        return this;
    }

    public CarPackage removeAttributes(CarAttribute... attributes) {
        for( CarAttribute attribute : attributes) {
            this.attributes.remove(attribute);
            this.packagePrice -= attribute.getSinglePrice() * 0.1;
        }
        return this;
    }

    public CarAttribute[] getAttributes() {
        CarAttribute[] array = new CarAttribute[attributes.size()];
        return attributes.toArray(array);
    }

    public float getPackagePrice() {
        return packagePrice;
    }
}

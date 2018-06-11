package edu.hm.shareit.models;

import javax.persistence.*;

@Entity
@Table(name = "TOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Car car;
    private CarPackage paket;
    private CarAttribute[] attributes;
    private float totalPrice;
    private ClimateZone climateZone;
    private boolean verified;

    public Order(){}

    public Order(Car car, CarPackage carPackage, ClimateZone climateZone, CarAttribute... attributes){
        this.car = car;
        this.paket = carPackage;
        this.climateZone = climateZone;
        this.attributes = attributes;
        this.totalPrice = 0;
        this.verified = false;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public CarPackage getPaket() {
        return paket;
    }

    public CarAttribute[] getAttributes() {
        return attributes;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public ClimateZone getClimateZone() {
        return climateZone;
    }

    public boolean isVerified() {
        return verified;
    }
}

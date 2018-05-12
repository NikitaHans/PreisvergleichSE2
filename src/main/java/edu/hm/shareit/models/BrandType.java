package edu.hm.shareit.models;

public class BrandType {

    private String name;
    private Brand brand;

    public BrandType(String name, Brand brand){
        this.name = name;
        this.brand = brand;
    }

    public String getName(){
        return this.name;
    }

    public Brand getBrand(){
        return this.brand;
    }
}

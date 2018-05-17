package edu.hm.shareit.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.LinkedList;
import java.util.List;

public class Brand {

    private String name;
    private List<BrandType> types;

    public Brand(String name){
        this.name = name;
        types = new LinkedList<>();
    }

    public Brand addType(BrandType type){
        types.add(type);
        return this;
    }

    public String getName(){
        return this.name;
    }

    public List<BrandType> getTypes(){
        return this.types;
    }
}

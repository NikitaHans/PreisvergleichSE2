package edu.hm.shareit.models;

import java.util.LinkedList;
import java.util.List;

public class Brand {

    private String name;
    private List<BrandType> types;

    public Brand(String name){
        this.name = name;
        types = new LinkedList<>();
    }

    public boolean addType(BrandType type){
        return types.add(type);
    }

    public String getName(){
        return this.name;
    }

    public List<BrandType> getTypes(){
        return this.types;
    }
}

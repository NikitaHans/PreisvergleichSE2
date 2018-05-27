package edu.hm.shareit.models;

import java.util.Objects;

public class Brand {

    private String name;

    public Brand(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

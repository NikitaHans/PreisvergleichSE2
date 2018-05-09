package edu.hm.shareit.Services;


import edu.hm.shareit.models.*;
import edu.hm.shareit.persistence.DatabaseManager;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager dbMan;


    /**
     * Constructor.
     * @param dbMan 
     */
    @Inject
    public CarService(DatabaseManager dbMan) {
        this.dbMan = dbMan;
    }

    public String submitOrder(Order Order){
        return "successful";
    }

    public Brand[] getBrands(){
        return new Brand[0];
    }

    public BrandType[] getTypes(Brand brand){
        return new BrandType[0];
    }

    public CarPaket[] getPakets(){
        return new CarPaket[0];
    }

    @Override
    public Attribute[] getAttributes() {
        return new Attribute[0];
    }

    @Override
    public BrandType[] getAllTypes() {
        return new BrandType[0];
    }
}

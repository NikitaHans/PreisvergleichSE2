package edu.hm.shareit.Services;


import edu.hm.shareit.models.*;
import edu.hm.shareit.persistence.DatabaseManager;
import org.omg.CORBA.INTERNAL;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager databaseManager;


    /**
     * Constructor.
     * @param databaseManager
     */
    @Inject
    public CarService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
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

    public CarPackage[] getPakets(){
        return new CarPackage[0];
    }

    @Override
    public CarAttribute[] getAttributes() {
        return new CarAttribute[0];
    }

    @Override
    public BrandType[] getAllTypes() {
        return new BrandType[0];
    }

    public CarDto[] submitOrder(OrderDto order){ return new CarDto[0]; }

}

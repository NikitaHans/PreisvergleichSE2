package edu.hm.shareit.Services;

import edu.hm.shareit.models.Car;
import edu.hm.shareit.persistence.DatabaseManager;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarServiceImpl implements CarService {

    private DatabaseManager dbMan;

    private static List<Car> test = new LinkedList<>();

    /**
     * Constructor.
     * @param dbMan 
     */
    @Inject
    public CarServiceImpl(DatabaseManager dbMan) {
        this.dbMan = dbMan;
    }

    public String submitProduct(Car car){
        test.add(car);
        return "successful";
    }

    public Car[] getProducts(){
        return test.toArray(new Car[test.size()]);
    }
}

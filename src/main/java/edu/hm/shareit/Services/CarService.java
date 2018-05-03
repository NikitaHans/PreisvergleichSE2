package edu.hm.shareit.Services;

import edu.hm.shareit.models.Car;
import edu.hm.shareit.persistence.DatabaseManager;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager databaseManager;

    private static List<Car> testCarList = new LinkedList<>();

    /**
     * Constructor.
     * @param databaseManager
     */
    @Inject
    public CarService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public String submitProduct(Car car){
        testCarList.add(car);
        return "successful";
    }

    public Car[] getProducts(){
        return testCarList.toArray(new Car[testCarList.size()]);
    }
}

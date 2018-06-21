package edu.hm.shareit.persistence;


import java.util.List;

import edu.hm.shareit.models.*;

/**
 * Manager interface for communication with database.
 * Implements persistance for service
 * 
 * @author Thomas Murschall
 *
 */
public interface DatabaseManagerFunctionality {
    
    /**
     * 
     * @param order
     */
    void insertOrder(Order order);

    void insertCar(Car car);

    void insertCarRackage(CarPackage carpackage);

    void insertCarAttribute(CarAttribute attribute);

    void insertClimateZone(ClimateZone zone);

    void insertNation(Nation nation);

    void insertUser(User user);

    /**
     * 
     * @return . 
     */

    List<Car> getAllCars();

    List<CarPackage> getAllPackages();

    List<CarAttribute> getAllCarAttributes();

    List<Order> getAllOrders();

    User getUser(String mail);

    Car getCar(String brand, String modelName);

    void init();
}

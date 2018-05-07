package edu.hm.shareit.persistence;


import java.util.List;

import edu.hm.shareit.models.Car;

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
     * @param car
     */
    void insertRequest(Car car);

    
    /**
     * 
     * @return . 
     */
    List getAllCars();
    
}

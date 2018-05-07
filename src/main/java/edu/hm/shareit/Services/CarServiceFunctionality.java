package edu.hm.shareit.Services;

import edu.hm.shareit.models.Car;

/**
 * @author Markus Krahl
 */
public interface CarServiceFunctionality {

    /**
     *
     * @return MediaServiceResult
     */
    Car[] getProducts();

    /**
     *
     * @return MediaServiceResult
     */
    String submitProduct(Car car);
}

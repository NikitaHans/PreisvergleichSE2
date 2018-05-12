package edu.hm.shareit.Services;

import edu.hm.shareit.models.*;

/**
 * @author Markus Krahl
 */
public interface CarServiceFunctionality {

    Brand[] getBrands();

    BrandType[] getTypes(Brand brand);

    BrandType[] getAllTypes();

    CarPackage[] getPakets();

    CarAttribute[] getAttributes();

    String submitOrder(Order order);
}

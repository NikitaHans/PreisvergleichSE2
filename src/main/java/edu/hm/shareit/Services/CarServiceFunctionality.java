package edu.hm.shareit.Services;

import edu.hm.shareit.models.*;

/**
 * @author Markus Krahl
 */
public interface CarServiceFunctionality {

    Brand[] getBrands();

    BrandType[] getTypes(Brand brand);

    BrandType[] getAllTypes();

    CarPaket[] getPakets();

    Attribute[] getAttributes();

    String submitOrder(Order order);
}

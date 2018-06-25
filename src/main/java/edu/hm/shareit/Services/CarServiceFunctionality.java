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

    Order[] submitOrder(Order order);

    String insertCar(Car car);

    String insertPackage (CarPackage carPackage);

    String insertAttribute (CarAttribute attribute);

    String insertNation (Nation nation);

    String insertUser(User user);

    String verifyUser(Login login);

    Order verifyOrder(Order order);

    boolean validUser (Token token);

    void init();
}

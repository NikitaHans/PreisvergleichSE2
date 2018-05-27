package edu.hm.shareit.Services;


import edu.hm.shareit.models.*;
import edu.hm.shareit.persistence.DatabaseManager;
import javax.inject.Inject;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager databaseManager;
    private RequestValidationService requestValidationService;
    public static final String SUCCESS_RESPONSE = "successful";
    public static final String FAILURE_RESPONSE = "failed";


    /**
     * Constructor.
     * @param databaseManager
     * @param requestValidationService
     */
    @Inject
    public CarService(DatabaseManager databaseManager, RequestValidationService requestValidationService) {
        this.databaseManager = databaseManager;
        this.requestValidationService = requestValidationService;
    }

    public String submitOrder(Order Order){
        return requestValidationService.determineIfValid(Order) ? SUCCESS_RESPONSE : FAILURE_RESPONSE;
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
}

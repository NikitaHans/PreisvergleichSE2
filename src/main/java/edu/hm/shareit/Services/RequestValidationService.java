package edu.hm.shareit.Services;

import edu.hm.shareit.persistence.DatabaseManager;
import org.apache.log4j.Logger;
import javax.inject.Inject;
import edu.hm.shareit.models.*;

import java.util.Arrays;

public class RequestValidationService {
    private final Logger log = Logger.getLogger(this.getClass());
    private DatabaseManager databaseManager;

    /**
     * Constructor.
     */
    @Inject
    RequestValidationService(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    public boolean determineIfValid(Order undefinedOrder){
        log.info("Determining if Order " + undefinedOrder + " is valid.");
        boolean result = checkName(undefinedOrder) &&
                        checkBrand(undefinedOrder) &&
                        checkPackage(undefinedOrder) &&
                        checkAttributes(undefinedOrder);
        log.info("Order " + undefinedOrder + " was found " + (result ? "valid" : "invalid"));
        if(result)
            forwardOrderAsValid(undefinedOrder);
        return result;
    }

    private boolean checkName(Order order) {
        return order.getName().equals("placeholder");
    }
    private boolean checkBrand(Order order) {
        return order.getBrand().equals(new Brand("placeholder"));
    }
    private boolean checkPackage(Order order) {
        return order.getPacket().equals(new CarPackage("placeholder"));
    }
    private boolean checkAttributes(Order order) {
        return Arrays.equals(order.getAttributes(), new CarAttribute[]{});
    }

    public void forwardOrderAsValid(Order validOrder) {
        log.info("Order " + validOrder + " being forwarded as valid.");
        //databaseManager.storeOrder(validOrder);
    }
}

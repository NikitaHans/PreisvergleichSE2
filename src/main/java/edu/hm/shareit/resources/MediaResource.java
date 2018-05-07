package edu.hm.shareit.resources;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.models.Car;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 * Created by Markus Krahl on 21.04.17.
 * That class is responsible for all requests.
 * Important is the URI-pattern-matching.
 * Each annotation can be related to a part of an URI-request
 */
@Path("/start")
public class MediaResource {

    private final CarServiceFunctionality carService;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Constructor of class.
     * carService is class for business logic, this is only an example and business logic is not used
     * @param carService
     */
    @Inject
    public MediaResource(CarServiceFunctionality carService) {
        this.carService = carService;
    }


    /**
     * Returns the sample created car.
     * @return Response with status
     */
    @GET
    @Path("/get")
    @Produces("application/json")
    public Response getBooks() {
        log.info("entered getBooks");
        Car[] list = carService.getProducts();
        ObjectMapper mapper = new ObjectMapper();
        String json = "\"Message\":\"error\"";
        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.warn("Encountered " + e.getClass());
        }
        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    /**
     * Insert a new car to the list.
     * @param car the new car
     * @return Response with status
     */
    @POST
    @Path("/post")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertCar(Car car) {
        log.info("entered insertCar");
        return Response
                .status(Response.Status.OK)
                .entity("\"status\":\"" + carService.submitProduct(car) + "\"")
                .build();
    }

}

package edu.hm.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarService;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.models.*;
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
@Path("/service")
public class MediaResource {

    private final CarServiceFunctionality carService;
    private final Logger log = Logger.getLogger(this.getClass());

    @Inject
    public MediaResource(CarServiceFunctionality carService) {
        this.carService = carService;
    }


    @GET
    @Path("/brands")
    @Produces("application/json")
    public Response getBrands() {
        log.info("Received getBrands request");
        return buildResponse(mapJson(carService.getBrands()));
    }

    @GET
    @Path("/attributes")
    @Produces("application/json")
    public Response getAttributes() {
        log.info("Received getAttributes request");
        return buildResponse(mapJson(carService.getAttributes()));
    }

    @GET
    @Path("/brandtypes")
    @Produces("application/json")
    public Response getAllTypes() {
        log.info("Received getAllTypes request");
        return buildResponse(mapJson(carService.getAllTypes()));
    }

    @GET
    @Path("/brandtypes/{brand}")
    @Produces("application/json")
    public Response getType(@PathParam("brand") Brand brand) {
        log.info("Received getType request");
        return buildResponse(mapJson(carService.getTypes(brand)));
    }

    @GET
    @Path("/packets")
    @Produces("application/json")
    public Response getPackets() {
        log.info("Received getPackets request");
        return buildResponse(mapJson(carService.getPakets()));
    }

    @POST
    @Path("/submit")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createCar(Order order) {
        log.info("Received createCar request");
        return buildResponse("\"status\":\"" + carService.submitOrder(order) + "\"");
    }

    //HELPER METHODS
    private String mapJson(Object[] list) {
        String response = mapJsonFunctionality(list);
        log.info("Response send was " + response);
        return response;
    }

    private String mapJsonFunctionality(Object[] list) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.warn("Encountered " + e.getClass());
            return "\"Message\":\"error\"";
        }
    }

    private Response buildResponse(String json) {
        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

}

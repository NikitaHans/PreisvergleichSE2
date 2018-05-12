package edu.hm.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarService;
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

    private final CarService carService;
    private final Logger log = Logger.getLogger(this.getClass());

    private String json;

    @Inject
    public MediaResource(CarService carService) {
        this.carService = carService;
    }


    @GET
    @Path("/brands")
    @Produces("application/json")
    public Response getBrands() {
        log.info("Received getBrands request");
        mapJson(carService.getBrands());
        return buildResponse();
    }

    @GET
    @Path("/attributes")
    @Produces("application/json")
    public Response getAttributes() {
        log.info("Received getAttributes request");
        mapJson(carService.getAttributes());
        return buildResponse();
    }

    @GET
    @Path("/brandtypes")
    @Produces("application/json")
    public Response getAllTypes() {
        log.info("Received getAllTypes request");
        mapJson(carService.getAllTypes());
        return buildResponse();
    }

    @GET
    @Path("/brandtypes/{brand}")
    @Produces("application/json")
    public Response getType(@PathParam("brand") Brand brand) {
        log.info("Received getType request");
        mapJson(carService.getTypes(brand));
        return buildResponse();
    }

    @GET
    @Path("/packets")
    @Produces("application/json")
    public Response getPackets() {
        log.info("Received getPackets request");
        mapJson(carService.getPakets());
        return buildResponse();
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
    private void mapJson(Object[] list) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.warn("Encountered " + e.getClass());
            json = "\"Message\":\"error\"";
        }
    }

    private Response buildResponse() {
        return buildResponse(null);
    }

    private Response buildResponse(String entity) {
        return Response
                .status(Response.Status.OK)
                .entity(entity == null ? json : entity)
                .build();
    }

}

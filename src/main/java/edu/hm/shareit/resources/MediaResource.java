package edu.hm.shareit.resources;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarService;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.models.*;

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

    private final CarServiceFunctionality cs;


    @Inject
    public MediaResource(CarService cs) {
        this.cs = cs;
    }


    @GET
    @Path("/brands")
    @Produces("application/json")
    public Response getBrands() {

        Brand[] list = cs.getBrands();
        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            json = "\"Message\":\"error\"";
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @GET
    @Path("/attributes")
    @Produces("application/json")
    public Response getAttributes() {

        Attribute[] list = cs.getAttributes();
        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            json = "\"Message\":\"error\"";
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @GET
    @Path("/brandtypes")
    @Produces("application/json")
    public Response getAllTypes() {

        BrandType[] list = cs.getAllTypes();
        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            json = "\"Message\":\"error\"";
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @GET
    @Path("/brandtypes/{brand}")
    @Produces("application/json")
    public Response getType(@PathParam("brand") Brand brand) {

        BrandType[] list = cs.getTypes(brand);
        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            json = "\"Message\":\"error\"";
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @GET
    @Path("/packets")
    @Produces("application/json")
    public Response getPackets() {

        CarPaket[] list = cs.getPakets();
        String json = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            json = "\"Message\":\"error\"";
        }

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @POST
    @Path("/submit")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createCar(Order order) {
        String message = cs.submitOrder(order);
        return Response
                .status(200)
                .entity("\"status\":\""+ message + "\"")
                .build();
    }

}

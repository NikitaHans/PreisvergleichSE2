package edu.hm.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.models.*;
import edu.hm.shareit.security.MySecurityManager;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


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
        carService.init();
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
    public Response createOrder(Order order) {
        log.info("Received createCar request");
        Token t = new Token();
        t.setToken(order.getNation());
        User user = MySecurityManager.getUser(t);
        order.setUser(user);
        order.setNation(user.getNation());
        return buildResponse(mapJson(carService.submitOrder(order)));
    }

    @POST
    @Path("/insert/car")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertCar(Car car) {
        log.info("Received insertCar request");
        return buildResponse("{\"status\":\"" + carService.insertCar(car) + "\"}");
    }

    @POST
    @Path("/insert/user")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertUser(User user) {
        log.info("Received insertCar request");
        return buildResponse("{\"status\":\"" + carService.insertUser(user) + "\"}");
    }

    @POST
    @Path("/verify/user")
    @Produces("application/json")
    @Consumes("application/json")
    public Response Login(Login loginAttempt) {
        log.info("Received insertCar request");
        return Response
                .status(Response.Status.OK)
                .entity("{\"token\":\""+ carService.verifyUser(loginAttempt) + "\"}")
                .build();
    }

    @POST
    @Path("/insert/package")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertPackage(CarPackage carPackage) {
        log.info("Received insertPackage request");
        return buildResponse("{\"status\":\"" + carService.insertPackage(carPackage) + "\"}");
    }

    @POST
    @Path("/insert/nation")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertZone(Nation nation) {
        log.info("Received insertZone request");
        return buildResponse("{\"status\":\"" + carService.insertNation(nation) + "\"}");
    }

    @POST
    @Path("/insert/attribute")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertAttribute(CarAttribute attribute) {
        log.info("Received insertAttribute request");
        return buildResponse("{\"status\":\"" + carService.insertAttribute(attribute) + "\"}");
    }

    @POST
    @Path("/validate")
    @Consumes("application/json")
    @Produces("application/json")
    public Response test(Token token) {
        return Response.status(200)
                .entity("{\"result\": \""+carService.validUser(token)+"\"}")
                .build();
    }

    //HELPER METHODS
    private String mapJson(Object... list) {
        String response = mapJsonFunctionality(list);
        log.info("Response send was " + response);
        return response;
    }

    private String mapJsonFunctionality(Object... list) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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


    private boolean checkToken(Token token){
        return carService.validUser(token);
    }

}

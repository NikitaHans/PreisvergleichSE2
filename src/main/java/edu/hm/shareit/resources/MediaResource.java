package edu.hm.shareit.resources;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarService;
import edu.hm.shareit.models.Car;

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

    private final CarService cs;
    private static final String CookieName = "Token";
    private final String LogInPage = "https://a4-auth-server-fancy-team-42.herokuapp.com/authenticate/auth/user";

    /**
     * Constructor of class.
     * cs is class for business logic, this is only an example and business logic is not used
     * @param cs
     */
    @Inject
    public MediaResource(CarService cs) {
        this.cs = cs;
    }


    /**
     * Returns the sample created car.
     * @return Response with status
     */
    @GET
    @Path("/get")
    @Produces("application/json")
    public Response getBooks() {

        Car[] list = cs.getProducts();
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

    /**
     * Insert a new book to the list.
     * @param car the new car
     * @return Response with status
     */
    @POST
    @Path("/post")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createCar(Car car) {
        String message = cs.submitProduct(car);
        return Response
                .status(200)
                .entity("\"status\":\""+ message + "\"")
                .build();
    }

}

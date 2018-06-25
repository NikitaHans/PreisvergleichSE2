package edu.hm.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.Services.CarService;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.models.*;
import org.apache.log4j.Logger;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;


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
    public Response createOrder(Order order) {
        log.info("Received createCar request");
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
        return buildResponse("{\"token\":\"" + carService.verifyUser(loginAttempt) + "\"}");
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
    @Path("/send/car_csv")
    @Produces("application/json")
    @Consumes("text/plain")
    public Response car_csv_import(String csv) {

        log.info("Received car_csv_import request:\n" + csv);
        CSVReader reader = new CSVReader(new StringReader(csv));

        String[] nextRecord;
        Boolean parseError = false;
        String returnMessage = "success";
        String brand, model;
        float price = 0;
        ArrayList<Car> newCars = new ArrayList<Car>();

        try {
            while ((nextRecord = reader.readNext()) != null && !parseError) {

                // Check if there are 3 entries per row
                if (nextRecord.length != 3){
                    returnMessage = "Parse error: Cars require 3 entries per row";
                    parseError = true;
                }

                // Check if price is a valid float number
                try{
                    price = Float.parseFloat(nextRecord[2]);
                } catch(NumberFormatException e) {
                    returnMessage = "Parse error: The third column has to be a valid float";
                    parseError = true;
                }

                // If everything was correct, add car to list
                if (!parseError){
                    brand = nextRecord[0];
                    model = nextRecord[1];
                    newCars.add(new Car(brand, model, price));
                }
            }
        } catch (IOException e) {
            parseError = true;
            log.error("IOException while reading CSV");
        }

        if (!parseError){
            boolean insertError = false;
            while (!insertError && !newCars.isEmpty()){
                Car car = newCars.remove(0);

                // If carService didn't return true, stop and report error
                if(!"success".equals(carService.insertCar(car))){
                    insertError = true;
                    returnMessage = "Failed to insert car";
                }
            }
        }

        return buildResponse("{\"status\":\"" + returnMessage + "\"}");
    }


    @POST
    @Path("/send/attribute_csv")
    @Produces("application/json")
    @Consumes("text/plain")
    public Response attribute_csv_import(String csv) {

        log.info("Received attribute_csv_import request:\n" + csv);
        CSVReader reader = new CSVReader(new StringReader(csv));

        String[] nextRecord;
        Boolean parseError = false;
        String returnMessage = "success";
        String name, zone;
        float price = 0;
        ArrayList<CarAttribute> newAttributes = new ArrayList<CarAttribute>();

        try {
            while ((nextRecord = reader.readNext()) != null && !parseError) {

                // Check if there are 3 entries per row
                if (nextRecord.length != 3){
                    returnMessage = "Parse error: Attributes require 3 entries per row";
                    parseError = true;
                }

                // Check if price is a valid float number
                try{
                    price = Float.parseFloat(nextRecord[2]);
                } catch(NumberFormatException e) {
                    returnMessage = "Parse error: The third column has to be a valid float";
                    parseError = true;
                }

                // If everything was correct, add attribute to list
                if (!parseError){
                    name = nextRecord[0];
                    zone = nextRecord[1];
                    newAttributes.add(new CarAttribute(name, new ClimateZone(zone), price));
                }
            }
        } catch (IOException e) {
            parseError = true;
            log.error("IOException while reading CSV");
        }

        if (!parseError) {
            boolean insertError = false;
            while (!insertError && !newAttributes.isEmpty()){
                CarAttribute attribute = newAttributes.remove(0);

                // If carService didn't return true, stop and report error
                if(!"success".equals(carService.insertAttribute(attribute))){
                    insertError = true;
                    returnMessage = "Failed to insert attribute";
                }
            }
        }

        return buildResponse("{\"status\":\"" + returnMessage + "\"}");
    }


    @POST
    @Path("/send/package_csv")
    @Produces("application/json")
    @Consumes("text/plain")
    public Response package_import(String csv) {

        log.info("Received package_csv_import request:\n" + csv);
        CSVReader reader = new CSVReader(new StringReader(csv));

        String[] nextRecord;
        Boolean parseError = false;
        String returnMessage = "success";
        String name;
        CarAttribute[] attributes;
        ArrayList<CarPackage> newPackages = new ArrayList<CarPackage>();

        try {
            while ((nextRecord = reader.readNext()) != null && !parseError) {

                // Initialize attribute array with correct number of entries
                attributes = new CarAttribute[nextRecord.length -1];

                // Check if there are at least 2 entries per row
                if (nextRecord.length < 3){
                    returnMessage = "Parse error: Packages require at least 3 entries per row";
                    parseError = true;
                }

                // If everything was correct, add car to list
                if (!parseError){
                    name = nextRecord[0];
                    for (int i = 1; i < nextRecord.length; i++){
                        // attributes[i-1]= ???
                    }
                    newPackages.add(new CarPackage());
                }
            }
        } catch (IOException e) {
            parseError = true;
            log.error("IOException while reading CSV");
        }

        if (!parseError){
            boolean insertError = false;
            while (!insertError && !newPackages.isEmpty()){
                CarPackage packg = newPackages.remove(0);

                // If carService didn't return true, stop and report error
                if(!"success".equals(carService.insertPackage(packg))){
                    insertError = true;
                    returnMessage = "Failed to insert car";
                }
            }
        }

        return buildResponse("{\"status\":\"" + returnMessage + "\"}");
    }


    //HELPER METHODS
    private String mapJson(Object... list) {
        String response = mapJsonFunctionality(list);
        log.info("Response sent was " + response);
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

}

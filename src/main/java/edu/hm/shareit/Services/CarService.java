package edu.hm.shareit.Services;


import edu.hm.shareit.models.*;
import edu.hm.shareit.persistence.DatabaseManager;
import edu.hm.shareit.security.MySecurityManager;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager databaseManager;
    private static boolean init = true;


    /**
     * Constructor.
     * @param databaseManager
     */
    @Inject
    public CarService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    public void init(){
        if(init) {
            databaseManager.init();
            init = false;
        }
    }

    public Order[] submitOrder(Order order){
        Car carOld = order.getCar();
        Car carNew = databaseManager.getCar(carOld.getBrand(), carOld.getModelName());
        order = verifyOrder(order);
        if (order.isVerified() && carNew != null) {
            order.setCar(carNew);

            float orderPrice = 0.f;
            for(CarAttribute attribute : order.getAttributes()){
                orderPrice += attribute.getSinglePrice();
            }
            orderPrice += order.getCar().getBasePrice();
            orderPrice += order.getPaket().getPackagePrice();
            orderPrice = orderPrice * 0.75f;
            order.setTotalPrice(orderPrice);

            this.databaseManager.insertOrder(order);
        }
        List<Order> allOrders = this.databaseManager.getAllOrders();

        List<Order> userOrders = new LinkedList<>();

        for(Order listOrder : allOrders){
            if(listOrder.getUser().equals(order.getUser())){
                userOrders.add(listOrder);
            }
        }

        int sizeOrder = userOrders.size();
        System.out.println("Size: " + sizeOrder);
        Order lastOrder = null;
        Order preLastOrder = null;

        if(sizeOrder > 1){
            long max = 0;
            for(Order listOrder : userOrders){
                System.out.println(listOrder.getId());
                if(listOrder.getId() > max && !order.equals(listOrder)){
                    max = listOrder.getId();
                    lastOrder = listOrder;
                }
            }
            max = 0;
            for(Order listOrder : userOrders){
                System.out.println(listOrder.getId());
                if(listOrder.getId() > max && !listOrder.equals(lastOrder)){
                    max = listOrder.getId();
                    preLastOrder = listOrder;
                }
            }
        }

        Order[] res = new Order[2];
        res[0] = order;
        res[1] = preLastOrder;
        return res;
    }

    @Override
    public String insertCar(Car car) {
        String res;

        try{
            databaseManager.insertCar(car);
            res = "success";
        }
        catch(Exception e){
            res = "Error occurred";
        }

        return res;
    }

    @Override
    public String insertPackage(CarPackage carPackage) {
        String res;

        try{
            databaseManager.insertCarRackage(carPackage);
            res = "success";
        }
        catch(Exception e){
            res = "Error occurred";
        }

        return res;
    }

    @Override
    public String insertAttribute(CarAttribute attribute) {
        String res;

        try{
            databaseManager.insertCarAttribute(attribute);
            res = "success";
        }
        catch(Exception e){
            res = "Error occurred";
        }

        return res;
    }

    @Override
    public String insertNation(Nation nation) {
        String res;

        try{
            databaseManager.insertNation(nation);
            res = "success";
        }
        catch(Exception e){
            res = "Error occurred";
        }

        return res;
    }

    @Override
    public String insertUser(User user) {
        String message = "Failed to create user";
        if(databaseManager.getUser(user.getMail()) == null){
            databaseManager.insertUser(user);
            message = "User created successfully.";
        }
        return message;
    }

    @Override
    public String verifyUser(Login login) {
        String res = "0";
        User user = databaseManager.getUser(login.getMail());
        if (user != null){
            if(user.getPassword().equals(login.getPassword())){
                res = MySecurityManager.getToken(user);
            }
        }
        return res;
    }

    public Brand[] getBrands(){
        List<Car> carList = getAllCars();

        Set<Brand> res = new HashSet<>();

        for(Car carObject : carList){
            res.add(new Brand(carObject.getBrand()));
        }

        Brand[] array = new Brand[res.size()];
        return res.toArray(array);
    }

    public BrandType[] getTypes(Brand brand){
        List<Car> carList = getAllCars();
        List<BrandType> res = new LinkedList<>();
        for (Car car : carList){
            if(car.getBrand().equals(brand.getName())){
                res.add(new BrandType(car.getModelName()));
            }
        }
        return res.toArray(new BrandType[res.size()]);
    }

    public CarPackage[] getPakets(){
        List<CarPackage> packageList = databaseManager.getAllPackages();
        return packageList.toArray(new CarPackage[packageList.size()]);
    }

    @Override
    public CarAttribute[] getAttributes() {
        List<CarAttribute> attributeList = databaseManager.getAllCarAttributes();
        return attributeList.toArray(new CarAttribute[attributeList.size()]);
    }

    @Override
    public BrandType[] getAllTypes() {
        List<Car> carList = getAllCars();

        List<BrandType> res = new LinkedList<>();

        for(Car car : carList){
            res.add(new BrandType(car.getModelName()));
        }

        return res.toArray(new BrandType[res.size()]);
    }

    public List<Car> getAllCars(){
        return databaseManager.getAllCars();
    }

    public boolean validUser(Token token){
        return MySecurityManager.validateToken(token);
    }

    @Override
    public Order verifyOrder( Order order) {
        Nation nation = databaseManager.getNation(order.getNation());
        ClimateZone climate = nation.getClimateZone();

        // All attributest of order
        List<CarAttribute> orderAttributes = new ArrayList<>();
        orderAttributes.addAll(order.getPaket().getAttributes());
        orderAttributes.addAll((order.getAttributes()));

        List<CarAttribute> allAtrribs = databaseManager.getAllCarAttributes();

        List<CarAttribute> mandatory = new ArrayList<CarAttribute>();

        for (Iterator<CarAttribute> iter = allAtrribs.iterator(); iter.hasNext();) {
            CarAttribute next = iter.next();
            if (climate.equals(next.getZone())) {
                mandatory.add(next);
            }
        }

        List<CarAttribute> climateAtr = new ArrayList<CarAttribute>();

        for (Iterator<CarAttribute> iter = orderAttributes.iterator(); iter.hasNext();) {
            CarAttribute next = iter.next();
            if (climate.equals(next.getZone())) {
                climateAtr.add(next);
            }
        }

        orderAttributes.removeAll(climateAtr);

        boolean checkMandatory = climateAtr.containsAll(mandatory);

        boolean optionalCheck = false;
        climate = new ClimateZone("optional");

        for (CarAttribute attribute : orderAttributes) {
            if (climate.equals(attribute.getZone())) {
                optionalCheck = true;
            }
            else{
                optionalCheck = false;
                break;
            }
        }

        order.setVerified(checkMandatory && optionalCheck);
        return order;
    }
}

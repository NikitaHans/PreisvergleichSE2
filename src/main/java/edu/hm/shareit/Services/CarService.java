package edu.hm.shareit.Services;


import edu.hm.shareit.models.*;
import edu.hm.shareit.persistence.DatabaseManager;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Markus Krahl, Thomas Murschallon 21.04.17.
 */
public class CarService implements CarServiceFunctionality {

    private DatabaseManager databaseManager;


    /**
     * Constructor.
     * @param databaseManager
     */
    @Inject
    public CarService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public String submitOrder(Order Order){
        return "successful";
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
    public String insertZone(ClimateZone zone) {
        String res;

        try{
            databaseManager.insertClimateZone(zone);
            res = "success";
        }
        catch(Exception e){
            res = "Error occurred";
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
}

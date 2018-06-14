package edu.hm.shareit.Services;

import edu.hm.shareit.models.*;

import java.util.*;

public class CarServiceMock implements CarServiceFunctionality {

    private ArrayList<Car> carList;
    private ArrayList<CarAttribute> attributeList;
    private ArrayList<CarPackage> packageList;

    private final String[] defaultBrands = {"BMW", "Audi", "Mercedes", "VW", "Skoda"};
    private final String[] defaultBrandTypes = {"M1", "M3", "A3", "A8", "S", "C", "Golf", "Tiguan", "Yeti", "Octavia"};

    public CarServiceMock(){
        setUpDefaultCars();
        setUpDefaultCarPackages();
    }

    private void setUpDefaultCars(){
        carList = new ArrayList<>();

        carList.add(new Car(defaultBrands[0], defaultBrandTypes[0], 10.000f));
        carList.add(new Car(defaultBrands[0], defaultBrandTypes[1], 10.000f));
        carList.add(new Car(defaultBrands[1], defaultBrandTypes[2], 10.000f));
        carList.add(new Car(defaultBrands[1], defaultBrandTypes[3], 10.000f));
        carList.add(new Car(defaultBrands[2], defaultBrandTypes[4], 10.000f));
        carList.add(new Car(defaultBrands[2], defaultBrandTypes[5], 10.000f));
        carList.add(new Car(defaultBrands[3], defaultBrandTypes[6], 10.000f));
        carList.add(new Car(defaultBrands[3], defaultBrandTypes[7], 10.000f));
        carList.add(new Car(defaultBrands[4], defaultBrandTypes[8], 10.000f));
        carList.add(new Car(defaultBrands[4], defaultBrandTypes[9], 10.000f));
    }

    private void setUpDefaultCarPackages() {
        packageList = new ArrayList<>();

        attributeList = new ArrayList<>();
        attributeList.add(new CarAttribute("Climacontrol", new ClimateZone("hot"), 10.000f));
        attributeList.add(new CarAttribute("Heating",  new ClimateZone("cold"), 10.000f));
        attributeList.add(new CarAttribute("Window winder",  new ClimateZone("optional"), 10.000f));
        attributeList.add(new CarAttribute("Snow chains",  new ClimateZone("cold"), 10.000f));

        HashMap<String, int[]>defaultCarPackages = new HashMap<>();
        defaultCarPackages.put("Sport",new int[]{0,3,5});
        defaultCarPackages.put("Normal",new int[]{0,1,4});
        defaultCarPackages.put("Premium",new int[]{0,1,2,3,4,5});

        for (Map.Entry<String, int[]> entry : defaultCarPackages.entrySet()) {
            CarPackage carPackage = new CarPackage(entry.getKey());
            for( int index : entry.getValue()) {
                carPackage.addAttributes(attributeList.get(index));
            }
            packageList.add(carPackage);
        }
    }

    @Override
    public Brand[] getBrands() {
        Set<Brand> res = new HashSet<>();

        for(Car carObject : carList){
            res.add(new Brand(carObject.getBrand()));
        }

        Brand[] array = new Brand[res.size()];
        return res.toArray(array);
    }

    @Override
    public BrandType[] getTypes(Brand brand) {
        List<BrandType> res = new LinkedList<>();
       for (Car car : carList){
           if(car.getBrand().equals(brand.getName())){
               res.add(new BrandType(car.getModelName()));
           }
       }
       return res.toArray(new BrandType[res.size()]);
    }

    @Override
    public BrandType[] getAllTypes() {
        List<BrandType> res = new LinkedList<>();

        for(Car car : carList){
            res.add(new BrandType(car.getModelName()));
        }

        return res.toArray(new BrandType[res.size()]);
    }

    @Override
    public CarPackage[] getPakets() {
        CarPackage[] array = new CarPackage[packageList.size()];
        return packageList.toArray(array);
    }

    @Override
    public CarAttribute[] getAttributes() {
        CarAttribute[] array = new CarAttribute[attributeList.size()];
        return attributeList.toArray(array);
    }

    @Override
    public Order submitOrder(Order order) {
        return order;
    }

    @Override
    public String insertCar(Car car) {
        return "success";
    }

    @Override
    public String insertPackage(CarPackage carPackage) {
        return "success";
    }

    @Override
    public String insertAttribute(CarAttribute attribute) {
        return "success";
    }

    @Override
    public String insertNation(Nation nation) {
        return null;
    }

    @Override
    public String insertUser(User user) {
        return null;
    }

    @Override
    public String verifyUser(Login login) {
        return null;
    }

    @Override
    public Order verifyOrder(Order order) {
        return null;
    }

}

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

        carList.add(new Car(defaultBrands[0], defaultBrandTypes[0]));
        carList.add(new Car(defaultBrands[0], defaultBrandTypes[1]));
        carList.add(new Car(defaultBrands[1], defaultBrandTypes[2]));
        carList.add(new Car(defaultBrands[1], defaultBrandTypes[3]));
        carList.add(new Car(defaultBrands[2], defaultBrandTypes[4]));
        carList.add(new Car(defaultBrands[2], defaultBrandTypes[5]));
        carList.add(new Car(defaultBrands[3], defaultBrandTypes[6]));
        carList.add(new Car(defaultBrands[3], defaultBrandTypes[7]));
        carList.add(new Car(defaultBrands[4], defaultBrandTypes[8]));
        carList.add(new Car(defaultBrands[4], defaultBrandTypes[9]));
    }

    private void setUpDefaultCarPackages() {
        packageList = new ArrayList<>();

        attributeList = new ArrayList<>();
        attributeList.add(new CarAttribute("Climacontrol", ClimateZone.HOT ));
        attributeList.add(new CarAttribute("Heating", ClimateZone.COLD));
        attributeList.add(new CarAttribute("Navigation", ClimateZone.NORMAL));
        attributeList.add(new CarAttribute("Audio", ClimateZone.NORMAL));
        attributeList.add(new CarAttribute("Window winder", ClimateZone.NORMAL));
        attributeList.add(new CarAttribute("Snow chains", ClimateZone.COLD));

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
    public String submitOrder(Order order) {
        return "{\"message\":\"successful\"}";
    }

    @Override
    public CarDto[] submitOrder(OrderDto order) {
        List<CarDto> cars = new ArrayList<>();
        Map<String, Object> type = new HashMap<>();
        type.put("name", "3");
        type.put("preis", 36000);

        Map<String, Object> paket = new HashMap<>();
        paket.put("name", "BlubPaket");
        paket.put("preis", 20000);

        Map<String, Object> attr = new HashMap<>();
        attr.put("name", "Fensterheber");
        attr.put("preis", 2000);

        List<Map<String, Object>> attributes = new ArrayList<>();
        attributes.add(attr);
        attributes.add(attr);

        CarDto car = new CarDto();
        car
                .setBrand("BMW")
                .setType(type)
                .setPaket(paket)
                .setAttributes(attributes)
                .setUberfuhrung(50);
        cars.add(car);
        cars.add(car);

        CarDto[] carss = new CarDto[cars.size()];
        carss = cars.toArray(carss);
        return carss;
    }
}

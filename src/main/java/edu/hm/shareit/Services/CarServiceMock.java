package edu.hm.shareit.Services;

import edu.hm.shareit.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarServiceMock implements CarServiceFunctionality {

    private ArrayList<Brand> brandList;
    private ArrayList<CarAttribute> attributeList;
    private ArrayList<CarPackage> packageList;

    private final String[] defaultBrands = {"BMW", "Audi", "Merceds", "VW", "Skoda"};
    private final String[] defaultBrandTypes = {"M1", "M3", "A3", "A8", "S", "C", "Golf", "Tiguan", "Yeti", "Octavia"};

    public CarServiceMock(){
        setUpDefaultBrands();
        setUpDefaultCarPackages();
    }

    private void setUpDefaultBrands() {
        int index = 0;
        brandList = new ArrayList<>();
        for( String brandName : defaultBrands) {
            brandList.add(
                    new Brand(brandName)
                            .addType(new BrandType(defaultBrandTypes[index]))
                            .addType(new BrandType(defaultBrandTypes[index + 1]))
            );
            index++;
        }
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
        Brand[] array = new Brand[brandList.size()];
        return brandList.toArray(array);
    }

    @Override
    public BrandType[] getTypes(Brand brand) {
       for (Brand brandA : brandList){
           if(brandA.getName().equals(brand.getName())){
               return (BrandType[]) brandA.getTypes().toArray();
           }
       }
       return new BrandType[0];
    }

    @Override
    public BrandType[] getAllTypes() {
        BrandType[] returnTypeArray = new BrandType[defaultBrandTypes.length];
        int index = 0;
        for(String brandTypeName : defaultBrandTypes) {
            returnTypeArray[index] = new BrandType(brandTypeName);
            index++;
        }
        return returnTypeArray;
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
}

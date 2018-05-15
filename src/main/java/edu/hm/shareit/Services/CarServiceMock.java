package edu.hm.shareit.Services;

import edu.hm.shareit.models.*;

import java.util.List;

public class CarServiceMock implements CarServiceFunctionality {

    Brand b1;
    Brand b2;
    Brand b3;
    Brand b4;
    Brand b5;

    Brand[] brandList;

    BrandType bt1;
    BrandType bt2;
    BrandType bt3;
    BrandType bt4;
    BrandType bt5;
    BrandType bt6;
    BrandType bt7;
    BrandType bt8;
    BrandType bt9;
    BrandType bt10;

    CarAttribute c1;
    CarAttribute c2;
    CarAttribute c3;
    CarAttribute c4;
    CarAttribute c5;
    CarAttribute c6;

    CarPackage cp1;
    CarPackage cp2;
    CarPackage cp3;

    BrandType[] brandTypeList;

    CarAttribute[] attributeList;

    CarPackage[] packageList;

    public CarServiceMock(){
        brandList = new Brand[5];
        brandTypeList = new BrandType[10];
        attributeList = new CarAttribute[6];
        packageList = new CarPackage[3];

        b1 = new Brand("BMW");
        b2 = new Brand("Audi");
        b3 = new Brand("Mercedes");
        b4 = new Brand("VW");
        b5 = new Brand("Skoda");

        bt1 = new BrandType("M!",b1);
        bt2 = new BrandType("M$",b1);
        bt3 = new BrandType("A3",b2);
        bt4 = new BrandType("A8",b2);
        bt5 = new BrandType("S",b3);
        bt6 = new BrandType("C",b3);
        bt7 = new BrandType("Golf",b4);
        bt8 = new BrandType("Tiguan",b4);
        bt9 = new BrandType("Yeti",b5);
        bt10 = new BrandType("Octavia",b5);

        c1 = new CarAttribute("Climacontrol", ClimateZone.HOT);
        c2 = new CarAttribute("Heating", ClimateZone.COLD);
        c3 = new CarAttribute("Navigation", ClimateZone.NORMAL);
        c4 = new CarAttribute("Audio", ClimateZone.NORMAL);
        c5 = new CarAttribute("Window winder", ClimateZone.NORMAL);
        c6 = new CarAttribute("Snow chains", ClimateZone.COLD);

        cp1 = new CarPackage("Sport", new CarAttribute[]{c1, c4, c6});
        cp2 = new CarPackage("Normal", new CarAttribute[]{c1, c2, c5});
        cp3 = new CarPackage("Premium", new CarAttribute[]{c1, c2, c3, c4, c5, c6});

        b1.addType(bt1);
        b1.addType(bt2);
        b2.addType(bt3);
        b2.addType(bt4);
        b3.addType(bt5);
        b3.addType(bt6);
        b4.addType(bt7);
        b4.addType(bt8);
        b5.addType(bt9);
        b5.addType(bt10);

        brandList[0] = b1;
        brandList[1] = b2;
        brandList[2] = b3;
        brandList[3] = b4;
        brandList[4] = b5;

        brandTypeList[0] = bt1;
        brandTypeList[1] = bt2;
        brandTypeList[2] = bt3;
        brandTypeList[3] = bt4;
        brandTypeList[4] = bt5;
        brandTypeList[5] = bt6;
        brandTypeList[6] = bt7;
        brandTypeList[7] = bt8;
        brandTypeList[8] = bt9;
        brandTypeList[9] = bt10;

        attributeList[0] = c1;
        attributeList[1] = c2;
        attributeList[2] = c3;
        attributeList[3] = c4;
        attributeList[4] = c5;
        attributeList[5] = c6;

        packageList[0] = cp1;
        packageList[1] = cp2;
        packageList[2] = cp3;
    }

    @Override
    public Brand[] getBrands() {
        return brandList;
    }

    @Override
    public BrandType[] getTypes(Brand brand) {
       BrandType[] res;

       for (int i = 0; i < brandList.length; i++){
           if(brandList[i].getName().equals(brand.getName())){
               List<BrandType> tmp = brandList[i].getTypes();
               res = tmp.toArray(new BrandType[tmp.size()]);
               return res;
           }
       }
       return res = new BrandType[0];
    }

    @Override
    public BrandType[] getAllTypes() {
        return brandTypeList;
    }

    @Override
    public CarPackage[] getPakets() {
        return packageList;
    }

    @Override
    public CarAttribute[] getAttributes() {
        return attributeList;
    }

    @Override
    public String submitOrder(Order order) {
        return "{\"message\":\"successful\"}";
    }
}

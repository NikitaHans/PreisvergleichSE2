package edu.hm.shareit.models;

public enum Climazone {
    HOT("hot",25,40),
    NORMAL("normal",10,25),
    COLD("cold",-20,10);

    private String name;
    private int lowTemp;
    private int highTemp;

    Climazone(String name, int lowTemp, int highTemp){
        this.name = name;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

}

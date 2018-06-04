package edu.hm.shareit.models;

import java.util.List;
import java.util.Map;

/**
 * Created by omer on 04.06.18.
 */
public class CarDto {

    private String brand;
    private Map<String, Object> type;
    private Map<String, Object> paket;
    private List<Map<String, Object>> attributes;
    private Integer uberfuhrung;

    public String getBrand() {
        return brand;
    }

    public CarDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Map<String, Object> getType() {
        return type;
    }

    public CarDto setType(Map<String, Object> type) {
        this.type = type;
        return this;
    }

    public Map<String, Object> getPaket() {
        return paket;
    }

    public CarDto setPaket(Map<String, Object> paket) {
        this.paket = paket;
        return this;
    }

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public CarDto setAttributes(List<Map<String, Object>> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Integer getUberfuhrung() {
        return uberfuhrung;
    }

    public CarDto setUberfuhrung(Integer uberfuhrung) {
        this.uberfuhrung = uberfuhrung;
        return this;
    }
}
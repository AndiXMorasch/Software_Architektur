package de.hsos.swa.kundenverwaltung.entity;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Vetoed;

@Dependent
public class Address {
    private int cityCode;
    private String city;
    private String street;
    private int houseNumber;

    public Address(){};

    public Address(int cityCode, String city, String street, int houseNumber) {
        this.cityCode = cityCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}

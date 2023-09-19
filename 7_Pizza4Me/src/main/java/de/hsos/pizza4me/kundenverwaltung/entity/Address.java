package de.hsos.pizza4me.kundenverwaltung.entity;

public class Address {
    private String cityCode;
    private String city;
    private String street;
    private String houseNumber;

    public Address() {

    }

    public Address(String cityCode, String city, String street, String houseNumber) {
        this.cityCode = cityCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}

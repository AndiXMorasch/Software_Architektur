package de.hsos.pizza4me.kundenverwaltung.gateway;

import de.hsos.pizza4me.kundenverwaltung.entity.Address;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEntity {

    private String cityCode;
    private String city;
    private String street;
    private String houseNumber;

    public static Address getAddressFromEntity(AddressEntity entity) {
        return new Address(entity.getCityCode(), entity.getCity(), entity.getStreet(), entity.getHouseNumber());
    }

    public static AddressEntity getAddressEntityFromAddress(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.cityCode = address.getCityCode();
        addressEntity.city = address.getCity();
        addressEntity.street = address.getStreet();
        addressEntity.houseNumber = address.getHouseNumber();
        return addressEntity;
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

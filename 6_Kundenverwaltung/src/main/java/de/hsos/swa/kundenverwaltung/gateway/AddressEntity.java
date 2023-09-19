package de.hsos.swa.kundenverwaltung.gateway;

import de.hsos.swa.kundenverwaltung.entity.Address;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Embeddable;

@Embeddable
@Vetoed
public class AddressEntity {
    private int cityCode;
    private String city;
    private String street;
    private int houseNumber;

    public static Address getAddressFromEntity(AddressEntity addressEntity) {
        return new Address(addressEntity.cityCode, addressEntity.city, addressEntity.street, addressEntity.houseNumber);
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

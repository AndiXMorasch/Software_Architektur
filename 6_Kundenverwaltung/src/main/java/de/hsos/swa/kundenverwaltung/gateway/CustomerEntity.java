package de.hsos.swa.kundenverwaltung.gateway;

import de.hsos.swa.kundenverwaltung.entity.Customer;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
@Vetoed
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String firstname;
    private String lastname;
    @Embedded
    private AddressEntity address;

    public static Customer getCustomerFromEntity(CustomerEntity customer) {
        return new Customer(customer.id, customer.firstname, customer.lastname, AddressEntity.getAddressFromEntity(customer.getAddress()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}

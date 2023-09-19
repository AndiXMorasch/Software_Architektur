package de.hsos.swa.kundenverwaltung.control;

import java.util.Collection;

import de.hsos.swa.kundenverwaltung.entity.Address;
import de.hsos.swa.kundenverwaltung.entity.Customer;

public interface ManageCustomers {
    
    Long createCustomer(String firstname, String lastname);

    Collection<Customer> getAllCustomers();

    Customer getCustomer(Long customerId);

    boolean deleteCustomer(Long customerId);

    boolean createAddress(Long customerId, Address address);

    boolean modifyAddress(Long customerId, Address newAddress);

    Address getAddress(Long customerId);

    boolean deleteAddress(Long customerId);
}

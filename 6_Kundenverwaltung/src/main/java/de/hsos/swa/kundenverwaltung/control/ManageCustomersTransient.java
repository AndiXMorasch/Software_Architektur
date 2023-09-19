package de.hsos.swa.kundenverwaltung.control;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import de.hsos.swa.kundenverwaltung.entity.Address;
import de.hsos.swa.kundenverwaltung.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ManageCustomersTransient {

    private ConcurrentHashMap<Long, Customer> customers = new ConcurrentHashMap<>();
    private Long idCreator = 0L;
    
    public Long createCustomer(String firstname, String lastname) {
        if(firstname != null && lastname != null) {
            Customer newCustomer = new Customer(firstname, lastname);
            newCustomer.setId(getNewCustomerId());
            newCustomer.setAddress(new Address());
            this.customers.put(newCustomer.getId(), newCustomer);
            return newCustomer.getId();
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return this.customers.values();
    }

    public Customer getCustomer(Long customerId) {
        return this.customers.get(customerId);
    }

    public boolean deleteCustomer(Long customerId) {
        if(this.customers.contains(customerId)) {
            this.customers.remove(customerId);
            return true;
        }
        return false;
    }

    public boolean createAddress(Long customerId, Address address) {
        if(this.customers.containsKey(customerId)) {
            this.customers.get(customerId).setAddress(address);
            return true;
        }
        return false;
    }

    public boolean modifyAddress(Long customerId, Address newAddress) {
        if(this.customers.containsKey(customerId)) {
            this.customers.get(customerId).setAddress(newAddress);
            return true;
        }
        return false;
    }

    public Address getAddress(Long customerId) {
        if(this.customers.containsKey(customerId)) {
            return this.customers.get(customerId).getAddress();
        }
        return null;
    }

    public boolean deleteAddress(Long customerId) {
        if(this.customers.containsKey(customerId)) {
            this.customers.get(customerId).setAddress(null);
            return true;
        }
        return false;
    }

    private Long getNewCustomerId() {
        return ++this.idCreator;
    }
}

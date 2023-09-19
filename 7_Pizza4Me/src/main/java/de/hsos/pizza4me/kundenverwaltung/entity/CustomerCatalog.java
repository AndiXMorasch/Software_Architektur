package de.hsos.pizza4me.kundenverwaltung.entity;

import java.util.Collection;

public interface CustomerCatalog {
    Collection<Customer> selectAllCustomers();

    Customer selectCustomer(Long customerId);

    Long createCustomer(Customer customer, String username, String password, String role);

    Customer modifyCustomer(Customer customer);

    boolean deleteCustomer(Long customerId);
}

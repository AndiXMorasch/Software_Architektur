package de.hsos.pizza4me.kundenverwaltung.control;

import java.util.Collection;

import de.hsos.pizza4me.kundenverwaltung.entity.Customer;

public interface ManageCustomers {
    Collection<Customer> selectAllCustomers();

    Customer selectCustomer(Long customerId);

    Long createCustomer(Customer customer, String username, String password, String role);

    Customer modifyCustomer(Customer customer);

    boolean deleteCustomer(Long customerId);
}

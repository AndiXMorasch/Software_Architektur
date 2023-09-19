package de.hsos.pizza4me.kundenverwaltung.gateway;

import de.hsos.pizza4me.kundenverwaltung.entity.Address;
import de.hsos.pizza4me.kundenverwaltung.entity.Customer;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
public class InitializeUsers {

    @Inject
    CustomerRepository repository;

    @Transactional
    public void initUsers(@Observes StartupEvent event) {
        Customer customer1 = new Customer();
        customer1.setName("Mickey Mouse");
        Address address1 = new Address("50355", "Entenhausen", "Am Freibad", "12");
        customer1.setAddress(address1);
        Customer customer2 = new Customer();
        customer2.setName("Donald Duck");
        Address address2 = new Address("50355", "Entenhausen", "Am Freibad", "14");
        customer2.setAddress(address2);
        this.repository.createCustomer(customer1, "mickey", "mouse", "admin");
        this.repository.createCustomer(customer2, "donald", "duck", "user");
    }
}

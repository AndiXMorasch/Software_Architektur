package de.hsos.pizza4me.kundenverwaltung.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.pizza4me.kundenverwaltung.entity.Address;
import de.hsos.pizza4me.kundenverwaltung.entity.Customer;
import de.hsos.pizza4me.kundenverwaltung.entity.CustomerCatalog;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
@Transactional(TxType.MANDATORY)
public class CustomerRepository implements CustomerCatalog {

    @Inject
    private EntityManager em;

    @Override
    public Collection<Customer> selectAllCustomers() {
        TypedQuery<CustomerEntity> findAll = em.createQuery("SELECT c FROM CustomerEntity c", CustomerEntity.class);
        List<CustomerEntity> entities = findAll.getResultList();
        return entities.stream()
                .map(CustomerEntity::getCustomerFromEntity)
                .toList();
    }

    @Override
    public Customer selectCustomer(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer-ID cannot be null.");
        }

        CustomerEntity entity = em.find(CustomerEntity.class, customerId);
        if (entity == null) {
            throw new NotFoundException("Customer with ID: '" + customerId + "' does not exist.");
        }
        return CustomerEntity.getCustomerFromEntity(entity);
    }

    @Override
    public Long createCustomer(Customer customer, String username, String password, String role) {
        if (!requestIsCorrect(customer)) {
            throw new IllegalArgumentException("Some of the customers data is missing.");
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setAddressEntity(AddressEntity.getAddressEntityFromAddress(customer.getAddress()));
        customerEntity.setUsername(username);
        customerEntity.setPassword(BcryptUtil.bcryptHash(password));
        customerEntity.setRole(role);

        this.em.persist(customerEntity);
        return customerEntity.getId();
    }

    @Override
    public Customer modifyCustomer(Customer customer) {
        if (!requestIsCorrect(customer)) {
            throw new IllegalArgumentException("Some of the customers data is missing.");
        }

        CustomerEntity entity = em.find(CustomerEntity.class, customer.getId());
        if (entity == null) {
            throw new NotFoundException("Customer with ID: '" + customer.getId() + "' does not exist.");
        }

        entity.setName(customer.getName());
        entity.setAddressEntity(AddressEntity.getAddressEntityFromAddress(customer.getAddress()));

        this.em.merge(entity);
        return customer;
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer-ID cannot be null.");
        }

        CustomerEntity customerEntity = em.find(CustomerEntity.class, customerId);
        em.remove(customerEntity);
        return customerEntity != null;
    }

    private boolean requestIsCorrect(Customer customer) {
        if (customer == null) {
            return false;
        }

        if (customer.getAddress() == null) {
            return false;
        }

        Address address = customer.getAddress();

        if (customer.getName().isEmpty() || address.getCityCode().isEmpty() ||
                address.getCity().isEmpty() || address.getStreet().isEmpty() || address.getHouseNumber().isEmpty()) {
            return false;
        }

        return true;
    }

}

package de.hsos.swa.kundenverwaltung.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.swa.kundenverwaltung.control.ManageCustomers;
import de.hsos.swa.kundenverwaltung.entity.Address;
import de.hsos.swa.kundenverwaltung.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository implements ManageCustomers {
    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public Long createCustomer(String firstname, String lastname) {
        if(firstname == null || lastname == null) {
            throw new IllegalArgumentException("Firstname and lastname cannot be empty.");
        }
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        AddressEntity addressEntity = new AddressEntity();
        entity.setAddress(addressEntity);
        this.em.persist(entity);
        return entity.getId();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        TypedQuery<CustomerEntity> findAll = this.em.createQuery("SELECT c FROM CustomerEntity c", CustomerEntity.class);
        List<CustomerEntity> entities = findAll.getResultList();
        return entities.stream().map(CustomerEntity::getCustomerFromEntity).toList();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        if(customerId == null) {
            throw new IllegalArgumentException("CustomerID cannot be null.");
        }
        CustomerEntity entity = this.em.find(CustomerEntity.class, customerId);
        if(entity == null) {
            throw new IllegalArgumentException("CustomerID " + customerId + " does not exist.");
        }
        return CustomerEntity.getCustomerFromEntity(entity);
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long customerId) {
        CustomerEntity entity = this.em.find(CustomerEntity.class, customerId);
        this.em.remove(entity);
        return entity != null;
    }

    @Override
    @Transactional
    public boolean createAddress(Long customerId, Address address) {
        if(customerId == null || address == null) {
            throw new IllegalArgumentException("CustomerId and address cannot be empty.");
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCityCode(address.getCityCode());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setHouseNumber(address.getHouseNumber());

        CustomerEntity customerEntity = this.em.find(CustomerEntity.class, customerId);
        customerEntity.setAddress(addressEntity);
        this.em.merge(customerEntity);

        return customerEntity.getAddress() != null;
    }

    @Override
    @Transactional
    public boolean modifyAddress(Long customerId, Address newAddress) {
        if(customerId == null || newAddress == null) {
            throw new IllegalArgumentException("CustomerId and new address cannot be empty.");
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCityCode(newAddress.getCityCode());
        addressEntity.setCity(newAddress.getCity());
        addressEntity.setStreet(newAddress.getStreet());
        addressEntity.setHouseNumber(newAddress.getHouseNumber());

        CustomerEntity customerEntity = this.em.find(CustomerEntity.class, customerId);
        customerEntity.setAddress(addressEntity);
        this.em.merge(customerEntity);

        return customerEntity.getAddress() != null;
    }

    @Override
    public Address getAddress(Long customerId) {
        if(customerId == null) {
            throw new IllegalArgumentException("CustomerId cannot be empty.");
        }
        CustomerEntity customerEntity = this.em.find(CustomerEntity.class, customerId);
        AddressEntity address = customerEntity.getAddress();
        return AddressEntity.getAddressFromEntity(address);
    }

    @Override
    @Transactional
    public boolean deleteAddress(Long customerId) {
        if(customerId == null) {
            throw new IllegalArgumentException("CustomerId cannot be empty.");
        }
        CustomerEntity customerEntity = this.em.find(CustomerEntity.class, customerId);
        customerEntity.setAddress(null);
        this.em.merge(customerEntity);
        return customerEntity.getAddress() == null;
    }
    
}

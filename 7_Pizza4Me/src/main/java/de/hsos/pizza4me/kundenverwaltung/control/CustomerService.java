package de.hsos.pizza4me.kundenverwaltung.control;

import java.util.Collection;

import de.hsos.pizza4me.kundenverwaltung.acl.AddressDTO;
import de.hsos.pizza4me.kundenverwaltung.acl.CustomerDTO;
import de.hsos.pizza4me.kundenverwaltung.acl.CustomerInformationProvider;
import de.hsos.pizza4me.kundenverwaltung.entity.Address;
import de.hsos.pizza4me.kundenverwaltung.entity.Customer;
import de.hsos.pizza4me.kundenverwaltung.entity.CustomerCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CustomerService implements ManageCustomers, CustomerInformationProvider {

    @Inject
    CustomerCatalog customerCatalog;

    @Override
    public Collection<Customer> selectAllCustomers() {
        return this.customerCatalog.selectAllCustomers();
    }

    @Override
    public Customer selectCustomer(Long customerId) {
        return this.customerCatalog.selectCustomer(customerId);
    }

    @Override
    public Long createCustomer(Customer customer, String username, String password, String role) {
        return this.customerCatalog.createCustomer(customer, username, password, role);
    }

    @Override
    public Customer modifyCustomer(Customer customer) {
        return this.customerCatalog.modifyCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        return this.customerCatalog.deleteCustomer(customerId);
    }

    @Override
    public CustomerDTO provideCustomerInfo(Long customerId) {
        Customer customer = this.customerCatalog.selectCustomer(customerId);
        if (customer == null) {
            return null;
        }
        return mapCustomerToCustomerDTO(customer);
    }

    private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.id = customer.getId();
        dto.name = customer.getName();
        dto.address = mapAddressToAddressDTO(customer.getAddress());
        return dto;
    }

    private AddressDTO mapAddressToAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.cityCode = address.getCityCode();
        dto.city = address.getCity();
        dto.street = address.getStreet();
        dto.houseNumber = address.getHouseNumber();
        return dto;
    }

}

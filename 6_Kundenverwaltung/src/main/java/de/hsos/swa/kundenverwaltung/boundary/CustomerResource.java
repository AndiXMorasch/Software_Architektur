package de.hsos.swa.kundenverwaltung.boundary;


import java.util.ArrayList;
import java.util.Collection;

import de.hsos.swa.kundenverwaltung.control.ManageCustomers;
import de.hsos.swa.kundenverwaltung.control.ManageCustomersTransient;
import de.hsos.swa.kundenverwaltung.entity.Address;
import de.hsos.swa.kundenverwaltung.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/customers")
public class CustomerResource {
    
    // Transiente Kundenverwaltung
    @Inject
    ManageCustomersTransient manageCustomers;

    // Persistente Kundenverwaltung
    // @Inject
    // ManageCustomers manageCustomers;

    @GET
    public Response getAllCustomers() {
        Collection<Customer> customers = this.manageCustomers.getAllCustomers();
        Collection<CustomerDTO> customersAsDTOs = mapCustomersToCustomerDTOs(customers);
        return Response.ok().entity(customersAsDTOs).build();
    }

    @POST
    public Response createCustomer(CustomerDTO customer) {
        Long customerId = this.manageCustomers.createCustomer(customer.firstname, customer.lastname);
        if(customerId != null) {
            return Response.ok(customerId).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{customerid}")
    public Response getCustomer(@PathParam("customerid") Long customerId) {
        Customer customer = this.manageCustomers.getCustomer(customerId);
        if(customer != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
            return Response.ok(customerDTO).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/{customerid}")
    public Response createAddress(@PathParam("customerid") Long customerId, AddressDTO address) {
        Address newAddress = mapAddressDTOToAddress(address);
        boolean created = this.manageCustomers.createAddress(customerId, newAddress);
        if(created) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/{customerid}")
    public Response modifyAddress(@PathParam("customerid") Long customerId, AddressDTO address) {
        Address newAddress = mapAddressDTOToAddress(address);
        boolean created = this.manageCustomers.modifyAddress(customerId, newAddress);
        if(created) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{customerid}")
    public Response deleteCustomer(@PathParam("customerid") Long customerId) {
        boolean deleted = this.manageCustomers.deleteCustomer(customerId);
        if(deleted) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{customerid}/address")
    public Response getCustomerAddress(@PathParam("customerid") Long customerId) {
        Address address = this.manageCustomers.getAddress(customerId);
        if(address != null) {
            AddressDTO addressDTO = mapAddressToAddressDTO(address);
            return Response.ok(addressDTO).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{customerid}/address")
    public Response deleteCustomerAddress(@PathParam("customerid") Long customerId) {
        boolean deleted = this.manageCustomers.deleteAddress(customerId);
        if(deleted) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    private Collection<CustomerDTO> mapCustomersToCustomerDTOs(Collection<Customer> customers) {
        ArrayList<CustomerDTO> customersAsDTOs = new ArrayList<>();
        for(Customer c : customers) {
            CustomerDTO customer = new CustomerDTO();
            customer.id = c.getId();
            customer.firstname = c.getFirstname();
            customer.lastname = c.getLastname();
            customer.address = mapAddressToAddressDTO(c.getAddress());
            customersAsDTOs.add(customer);
        }
        return customersAsDTOs;
    }

    private AddressDTO mapAddressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.city = address.getCity();
        addressDTO.cityCode = address.getCityCode();
        addressDTO.street = address.getStreet();
        addressDTO.houseNumber = address.getHouseNumber();
        return addressDTO;
    }

    private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.id = customer.getId();
        customerDTO.firstname = customer.getFirstname();
        customerDTO.lastname = customer.getLastname();
        customerDTO.address = mapAddressToAddressDTO(customer.getAddress());
        return customerDTO;
    }

    private Address mapAddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCity(addressDTO.city);
        address.setCityCode(addressDTO.cityCode);
        address.setStreet(addressDTO.street);
        address.setHouseNumber(addressDTO.houseNumber);
        return address;
    }
}

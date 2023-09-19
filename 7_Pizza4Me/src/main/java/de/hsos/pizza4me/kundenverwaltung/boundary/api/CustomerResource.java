package de.hsos.pizza4me.kundenverwaltung.boundary.api;

import java.util.Collection;

import de.hsos.pizza4me.kundenverwaltung.control.ManageCustomers;
import de.hsos.pizza4me.kundenverwaltung.entity.Address;
import de.hsos.pizza4me.kundenverwaltung.entity.Customer;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@RequestScoped
@Transactional(TxType.REQUIRES_NEW)
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class CustomerResource {

    @Inject
    ManageCustomers manageCustomers;

    @GET
    @RolesAllowed("admin")
    public Response getAllCustomers() {
        Collection<Customer> customers = this.manageCustomers.selectAllCustomers();
        Collection<CustomerDTO> dtos = customers.stream().map(this::mapCustomerToDTO).toList();
        return Response.ok()
                .entity(dtos)
                .build();
    }

    @POST
    public Response createCustomer(@Valid CustomerDTO customer, @QueryParam("username") String username,
            @QueryParam("password") String password, @QueryParam("role") String role) {
        if (username == null || password == null || role == null) {
            return Response.status(400, "Username, Password and Role must be given").build();
        }
        Long customerId = this.manageCustomers.createCustomer(mapCustomerDTOToCustomer(customer), username, password,
                role);
        if (customerId != null) {
            return Response.ok(customerId).build();
        }
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed("admin")
    @Path("/{customerid}")
    public Response getCustomerDetails(@PathParam("customerid") Long customerId) {
        if (customerId != null) {
            Customer customer = this.manageCustomers.selectCustomer(customerId);
            CustomerDTO customerDTO = mapCustomerToDTO(customer);
            return Response.ok(customerDTO).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @RolesAllowed("admin")
    @Path("/{customerid}")
    public Response modifyCustomer(@PathParam("customerid") Long customerId, @Valid CustomerDTO customerDTO) {
        Customer customer = this.manageCustomers.modifyCustomer(mapCustomerDTOToCustomer(customerDTO));
        if (customer != null) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{customerid}")
    public Response deleteCustomer(@PathParam("customerid") Long customerId) {
        boolean deleted = this.manageCustomers.deleteCustomer(customerId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    private CustomerDTO mapCustomerToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.id = customer.getId();
        dto.name = customer.getName();
        dto.address = this.mapAddressToDTO(customer.getAddress());
        return dto;
    }

    private AddressDTO mapAddressToDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.cityCode = address.getCityCode();
        dto.city = address.getCity();
        dto.street = address.getStreet();
        dto.houseNumber = address.getHouseNumber();
        return dto;
    }

    private Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.name);
        customer.setAddress(this.mapAddressDTOToAddress(customerDTO.address));
        return customer;
    }

    private Address mapAddressDTOToAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setCityCode(addressDTO.cityCode);
        address.setCity(addressDTO.city);
        address.setStreet(addressDTO.street);
        address.setHouseNumber(addressDTO.houseNumber);
        return address;
    }
}

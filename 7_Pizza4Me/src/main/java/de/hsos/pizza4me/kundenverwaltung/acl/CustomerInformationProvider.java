package de.hsos.pizza4me.kundenverwaltung.acl;

public interface CustomerInformationProvider {

    CustomerDTO provideCustomerInfo(Long customerId);
}

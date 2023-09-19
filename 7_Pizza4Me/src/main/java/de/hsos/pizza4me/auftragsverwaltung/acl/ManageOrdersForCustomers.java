package de.hsos.pizza4me.auftragsverwaltung.acl;

public interface ManageOrdersForCustomers {
    
    void deleteAllOrdersForCustomer(Long customerId);
}
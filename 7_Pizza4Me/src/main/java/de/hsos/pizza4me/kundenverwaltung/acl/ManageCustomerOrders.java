package de.hsos.pizza4me.kundenverwaltung.acl;

public interface ManageCustomerOrders {
    
    void deleteAllOrdersForCustomer(Long customerId);
}

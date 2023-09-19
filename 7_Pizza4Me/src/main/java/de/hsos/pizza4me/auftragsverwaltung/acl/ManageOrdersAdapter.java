package de.hsos.pizza4me.auftragsverwaltung.acl;

import de.hsos.pizza4me.kundenverwaltung.acl.ManageCustomerOrders;
import jakarta.inject.Inject;

public class ManageOrdersAdapter implements ManageCustomerOrders {
    
    @Inject
    ManageOrdersForCustomers manageOrdersForCustomers;

    @Override
    public void deleteAllOrdersForCustomer(Long customerId) {
        this.manageOrdersForCustomers.deleteAllOrdersForCustomer(customerId);
    }
    
}

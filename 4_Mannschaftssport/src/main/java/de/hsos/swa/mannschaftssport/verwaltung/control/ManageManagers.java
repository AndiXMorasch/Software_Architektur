package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;

public interface ManageManagers {
    Manager selectManager(Long managerId);

    Collection<Manager> selectAllManagers(String name);

    Manager createManager(Manager manager);

    Manager modifyManager(Manager manager);

    boolean deleteManager(Long managerId);
}

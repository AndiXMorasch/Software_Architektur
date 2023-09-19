package de.hsos.swa.mannschaftssport.verwaltung.entity;

import java.util.Collection;

public interface ManagerKatalog {
    Manager selectManager(Long managerId);

    Collection<Manager> selectAllManagers();

    Collection<Manager> selectAllManagers(String name);

    Manager createManager(Manager manager);

    Manager modifyManager(Manager manager);

    boolean deleteManager(Long managerId);
}

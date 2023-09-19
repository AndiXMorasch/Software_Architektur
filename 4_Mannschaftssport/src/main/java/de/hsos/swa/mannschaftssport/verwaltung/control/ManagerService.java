package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.ManagerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;

@ApplicationScoped
public class ManagerService {

    @Inject
    @Transient
    private ManagerKatalog managerKatalog;

    public Collection<Manager> getAllManagers(String name) {
        if(name == null) {
            return this.managerKatalog.selectAllManagers();
        }
        return this.managerKatalog.selectAllManagers(name);
    }

    public Manager createManager(Manager manager) {
        return this.managerKatalog.createManager(manager);
    }

    public Manager getManagerById(Long id) {
        return this.managerKatalog.selectManager(id);
    }

    public Manager modifyManager(Manager manager) {
        return this.managerKatalog.modifyManager(manager);
    }

    public boolean deleteManager(Long id) {
        return this.deleteManager(id);
    }
}

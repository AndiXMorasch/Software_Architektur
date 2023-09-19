package de.hsos.swa.mannschaftssport.verwaltung.gateway;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.ManagerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
@Transient
public class ManagerRepository implements ManagerKatalog {
    private ConcurrentHashMap<Long, Manager> managers = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        managers.put(1L, new Manager(1L, "Thomas Tuchel"));
        managers.put(2L, new Manager(2L, "Juergen Klopp"));
        managers.put(3L, new Manager(3L, "Pep Guardiola"));
        managers.put(4L, new Manager(4L, "Carlo Ancelotti"));
    }

    @Override
    public Manager selectManager(Long managerId) {
        if (managerId == null) {
            throw new IllegalArgumentException("managerId cannot be null");
        }

        return this.managers.get(managerId);
    }

    @Override
    public Collection<Manager> selectAllManagers() {
        return this.managers.values();
    }

    @Override
    public Collection<Manager> selectAllManagers(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        return this.managers.values().stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Manager createManager(Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("manager cannot be null");
        }

        manager.setId((long) (Math.random() * 10000));
        this.managers.put(manager.getId(), manager);
        return manager;
    }

    @Override
    public Manager modifyManager(Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("manager cannot be null");
        }

        Manager m = this.managers.get(manager.getId());
        if(m == null) {
            return null;
        }

        m.setName(manager.getName());
        return m;
    }

    @Override
    public boolean deleteManager(Long managerId) {
        if (managerId == null) {
            throw new IllegalArgumentException("managerId cannot be null");
        }

        Manager removed = this.managers.remove(managerId);
        return removed != null;
    }
}

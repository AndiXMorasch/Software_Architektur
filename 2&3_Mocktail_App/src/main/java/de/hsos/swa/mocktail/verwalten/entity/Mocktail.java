package de.hsos.swa.mocktail.verwalten.entity;

import java.util.Collection;
import java.util.LinkedList;

public class Mocktail {
    private Long id;
    private String name;
    private String beschreibung;
    private Collection<Zutat> zutaten = new LinkedList<>();

    public Mocktail() {
    }

    public Mocktail(Long id, String name, String beschreibung, Collection<Zutat> zutaten) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.zutaten = zutaten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(Collection<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((beschreibung == null) ? 0 : beschreibung.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mocktail other = (Mocktail) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (beschreibung == null) {
            if (other.beschreibung != null)
                return false;
        } else if (!beschreibung.equals(other.beschreibung))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Mocktail [id=" + id + ", name=" + name + ", beschreibung=" + beschreibung + ", zutaten=" + zutaten
                + "]";
    }

}

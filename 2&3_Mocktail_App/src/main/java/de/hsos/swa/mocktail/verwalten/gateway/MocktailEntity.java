package de.hsos.swa.mocktail.verwalten.gateway;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
class MocktailEntity {
    @Id
    @SequenceGenerator(name = "mocktailSeq", sequenceName = "mocktail_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "mocktailSeq")
    private Long id;
    private String name;
    private String beschreibung;

    @OneToMany(mappedBy = "mocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZutatEntity> zutaten = new LinkedList<>();

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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public List<ZutatEntity> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<ZutatEntity> zutaten) {
        this.zutaten = zutaten;
    }

}

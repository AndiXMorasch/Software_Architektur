package de.hsos.swa.mocktail.verwalten.gateway;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import de.hsos.swa.mocktail.verwalten.entity.Einheit;

@Entity
public class ZutatEntity {
    @Id
    @SequenceGenerator(name = "zutatSeq", sequenceName = "zutat_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "zutatSeq")
    private Long id;

    private String name;

    private float menge;

    @Enumerated
    private Einheit einheit;

    @ManyToOne
    @JoinColumn(name = "mocktail_id")
    private MocktailEntity mocktail;

    public ZutatEntity() {
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

    public float getMenge() {
        return menge;
    }

    public void setMenge(float menge) {
        this.menge = menge;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public MocktailEntity getMocktail() {
        return mocktail;
    }

    public void setMocktail(MocktailEntity mocktail) {
        this.mocktail = mocktail;
    }

}

package de.hsos.swa.mocktail.verwalten.entity;

public class Zutat {
    private String name;
    private float menge;
    private Einheit einheit;

    public Zutat() {
    }

    public Zutat(String name, float menge, Einheit einheit) {
        this.name = name;
        this.menge = menge;
        this.einheit = einheit;
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

    @Override
    public String toString() {
        return "Zutat [name=" + name + ", menge=" + menge + ", einheit=" + einheit + "]";
    }

}

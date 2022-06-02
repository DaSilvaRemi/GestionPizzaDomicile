package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Ingredients {
    private int id;
    private String nom;

    public Ingredients(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}

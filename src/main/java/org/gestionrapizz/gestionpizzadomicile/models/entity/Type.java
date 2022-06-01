package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Type {
    private String id;
    private String nom;

    public Type(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "Type{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}

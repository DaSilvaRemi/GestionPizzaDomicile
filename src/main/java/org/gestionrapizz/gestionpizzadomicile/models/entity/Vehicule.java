package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Vehicule {
    String immatriculation;
    Type type;

    public Vehicule(String immatriculation, Type type) {
        this.immatriculation = immatriculation;
        this.type = type;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", type=" + type +
                '}';
    }
}

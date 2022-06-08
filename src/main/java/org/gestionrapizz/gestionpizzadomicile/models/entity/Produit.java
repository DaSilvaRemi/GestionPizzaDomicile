package org.gestionrapizz.gestionpizzadomicile.models.entity;

import java.util.HashMap;
import java.util.List;

public class Produit {
    private Pizza pizza;
    private Taille taille;

    private double prixProduit;

    public Produit(Pizza pizza, Taille taille, double prixProduit) {
        this.pizza = pizza;
        this.taille = taille;
        this.prixProduit = prixProduit;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }
}

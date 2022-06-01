package org.gestionrapizz.gestionpizzadomicile.models.entity;

import java.util.HashMap;
import java.util.List;

public class Produit {
    Pizza pizza;
    Taille taille;

    public Produit(Pizza pizza, Taille taille) {
        this.pizza = pizza;
        this.taille = taille;
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
}

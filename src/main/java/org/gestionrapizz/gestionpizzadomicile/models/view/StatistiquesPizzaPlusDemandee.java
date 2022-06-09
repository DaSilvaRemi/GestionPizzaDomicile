package org.gestionrapizz.gestionpizzadomicile.models.view;

public class StatistiquesPizzaPlusDemandee {
    String nom;
    int nbCommandesPizza;

    public StatistiquesPizzaPlusDemandee(String nom, int nbCommandesPizza) {
        this.nom = nom;
        this.nbCommandesPizza = nbCommandesPizza;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbCommandesPizza() {
        return nbCommandesPizza;
    }

    public void setNbCommandesPizza(int nbCommandesPizza) {
        this.nbCommandesPizza = nbCommandesPizza;
    }
}

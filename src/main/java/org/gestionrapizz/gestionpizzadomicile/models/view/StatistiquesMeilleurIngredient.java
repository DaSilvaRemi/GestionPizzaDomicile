package org.gestionrapizz.gestionpizzadomicile.models.view;

public class StatistiquesMeilleurIngredient {
    String nom;
    int nbApparition;

    public StatistiquesMeilleurIngredient(String nom, int nbApparition) {
        this.nom = nom;
        this.nbApparition = nbApparition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbApparition() {
        return nbApparition;
    }

    public void setNbApparition(int nbApparition) {
        this.nbApparition = nbApparition;
    }
}

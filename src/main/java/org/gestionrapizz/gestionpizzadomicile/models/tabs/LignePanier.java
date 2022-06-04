package org.gestionrapizz.gestionpizzadomicile.models.tabs;

public class LignePanier
{
    private String nomPizza;
    private String taille;
    private Double prix;

    public LignePanier(String nomPizza, String taille, Double prix) {
        this.nomPizza = nomPizza;
        this.taille = taille;
        this.prix = prix;
    }
}

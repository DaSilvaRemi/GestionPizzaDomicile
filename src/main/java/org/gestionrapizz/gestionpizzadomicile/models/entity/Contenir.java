package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Contenir {
    private Commande commande;
    private Produit produit;

    public Contenir(Commande commande, Produit produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Contenir{" +
                "commande=" + commande +
                ", produit=" + produit +
                '}';
    }
}

package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;

import java.sql.Timestamp;

public class MesCommandes {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty dateHeureCommande;
    private final SimpleStringProperty dateHeureLivraison;
    private final SimpleStringProperty statut;
    private final SimpleStringProperty livreur;

    private final SimpleDoubleProperty montant;


    public MesCommandes(Commande commande){
        this.id = new SimpleIntegerProperty(commande.getId());
        this.dateHeureCommande = new SimpleStringProperty(commande.getDateHeure() == null ? "Aucune date" : commande.getDateHeure().toString());
        this.dateHeureLivraison = new SimpleStringProperty(commande.getDateHeureLivraison() == null ? "Aucune date" : commande.getDateHeureLivraison().toString());
        this.statut = new SimpleStringProperty(commande.getStatut().getNom());
        this.livreur = new SimpleStringProperty(commande.getLivreur().getNom());
        this.montant = new SimpleDoubleProperty(commande.getMontant());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDateHeureCommande() {
        return dateHeureCommande.get();
    }

    public SimpleStringProperty dateHeureCommandeProperty() {
        return dateHeureCommande;
    }

    public void setDateHeureCommande(String dateHeureCommande) {
        this.dateHeureCommande.set(dateHeureCommande);
    }

    public String getDateHeureLivraison() {
        return dateHeureLivraison.get();
    }

    public SimpleStringProperty dateHeureLivraisonProperty() {
        return dateHeureLivraison;
    }

    public void setDateHeureLivraison(String dateHeureLivraison) {
        this.dateHeureLivraison.set(dateHeureLivraison);
    }

    public String getStatut() {
        return statut.get();
    }

    public SimpleStringProperty statutProperty() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut.set(statut);
    }

    public String getLivreur() {
        return livreur.get();
    }

    public SimpleStringProperty livreurProperty() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur.set(livreur);
    }

    public double getMontant() {
        return montant.get();
    }

    public SimpleDoubleProperty montantProperty() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant.set(montant);
    }
}

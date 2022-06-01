package org.gestionrapizz.gestionpizzadomicile.models.entity;
import java.sql.Timestamp;

public class Commande {
    private int id;
    private Timestamp dateHeure;
    private Timestamp dateHeureLivraison;
    private double montant;
    private boolean retard;
    private Client client;
    private Livreur livreur;
    private Statut statut;

    public Commande(int id, Timestamp dateHeure, Client client, Livreur livreur, Statut statut) {
        this(id, dateHeure, null, 0.0, false, client, livreur, statut);
    }

    public Commande(int id, Timestamp dateHeure, Timestamp dateHeureLivraison, double montant, boolean retard, Client client, Livreur livreur, Statut statut) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.dateHeureLivraison = dateHeureLivraison;
        this.montant = montant;
        this.retard = retard;
        this.client = client;
        this.livreur = livreur;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Timestamp getDateHeureLivraison() {
        return dateHeureLivraison;
    }

    public void setDateHeureLivraison(Timestamp dateHeureLivraison) {
        this.dateHeureLivraison = dateHeureLivraison;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", dateHeure=" + dateHeure +
                ", dateHeureLivraison=" + dateHeureLivraison +
                ", montant=" + montant +
                ", retard=" + retard +
                ", client=" + client +
                ", livreur=" + livreur +
                ", statut=" + statut +
                '}';
    }
}

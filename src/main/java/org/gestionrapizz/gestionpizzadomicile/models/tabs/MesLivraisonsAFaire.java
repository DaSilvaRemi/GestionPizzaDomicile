package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;


public class MesLivraisonsAFaire {
    private final SimpleStringProperty dateCommande;
    private final SimpleDoubleProperty montant;
    private final SimpleStringProperty immatriculation;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telephone;
    private final SimpleStringProperty adresse_rue;
    private final SimpleStringProperty adresse_ville;
    private final SimpleStringProperty adresse_codepostal;

    public MesLivraisonsAFaire(Commande commande) {
        this.dateCommande = new SimpleStringProperty(commande.getDateHeure().toString());
        this.montant = new SimpleDoubleProperty(commande.getMontant());
        this.immatriculation = new SimpleStringProperty(commande.getVehicule().getImmatriculation());
        this.nom = new SimpleStringProperty(commande.getClient().getNom());
        this.prenom = new SimpleStringProperty(commande.getClient().getPrenom());
        this.email = new SimpleStringProperty(commande.getClient().getEmail());
        this.telephone = new SimpleStringProperty(commande.getClient().getTelephone());
        this.adresse_rue = new SimpleStringProperty(commande.getClient().getAdresseRue());
        this.adresse_ville = new SimpleStringProperty(commande.getClient().getVille());
        this.adresse_codepostal = new SimpleStringProperty(commande.getClient().getCodePostal());
    }

    public String getDateCommande() {
        return dateCommande.get();
    }

    public SimpleStringProperty dateCommandeProperty() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande.set(dateCommande);
    }

    public double getMontant() {
        return montant.get();
    }

    public SimpleDoubleProperty montantProperty() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant.set(montant);
    }

    public String getImmatriculation() {
        return immatriculation.get();
    }

    public SimpleStringProperty immatriculationProperty() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation.set(immatriculation);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getAdresse_rue() {
        return adresse_rue.get();
    }

    public SimpleStringProperty adresse_rueProperty() {
        return adresse_rue;
    }

    public void setAdresse_rue(String adresse_rue) {
        this.adresse_rue.set(adresse_rue);
    }

    public String getAdresse_ville() {
        return adresse_ville.get();
    }

    public SimpleStringProperty adresse_villeProperty() {
        return adresse_ville;
    }

    public void setAdresse_ville(String adresse_ville) {
        this.adresse_ville.set(adresse_ville);
    }

    public String getAdresse_codepostal() {
        return adresse_codepostal.get();
    }

    public SimpleStringProperty adresse_codepostalProperty() {
        return adresse_codepostal;
    }

    public void setAdresse_codepostal(String adresse_codepostal) {
        this.adresse_codepostal.set(adresse_codepostal);
    }
}

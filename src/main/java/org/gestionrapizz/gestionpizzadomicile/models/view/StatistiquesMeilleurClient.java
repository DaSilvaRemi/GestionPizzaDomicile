package org.gestionrapizz.gestionpizzadomicile.models.view;

public class StatistiquesMeilleurClient {
    int id_utilisateur;
    String nom;
    String prenom;
    String email;
    String motdepasse;
    String telephone;
    String adresse_rue;
    String adresse_codepostal;
    String adresse_ville;
    double solde;
    int montantTotal;

    public StatistiquesMeilleurClient(int id_utilisateur, String nom, String prenom, String email, String motdepasse, String telephone, String adresse_rue, String adresse_codepostal, String adresse_ville, double solde, int montantTotal) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.telephone = telephone;
        this.adresse_rue = adresse_rue;
        this.adresse_codepostal = adresse_codepostal;
        this.adresse_ville = adresse_ville;
        this.solde = solde;
        this.montantTotal = montantTotal;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse_rue() {
        return adresse_rue;
    }

    public void setAdresse_rue(String adresse_rue) {
        this.adresse_rue = adresse_rue;
    }

    public String getAdresse_codepostal() {
        return adresse_codepostal;
    }

    public void setAdresse_codepostal(String adresse_codepostal) {
        this.adresse_codepostal = adresse_codepostal;
    }

    public String getAdresse_ville() {
        return adresse_ville;
    }

    public void setAdresse_ville(String adresse_ville) {
        this.adresse_ville = adresse_ville;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(int montantTotal) {
        this.montantTotal = montantTotal;
    }
}

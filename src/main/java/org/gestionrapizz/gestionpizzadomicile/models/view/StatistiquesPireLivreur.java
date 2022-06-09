package org.gestionrapizz.gestionpizzadomicile.models.view;

public class StatistiquesPireLivreur {
    int id_utilisateur;
    String nom;
    String prenom;
    String email;
    String motdepasse;
    String immatriculation;
    int nbRetards;

    public StatistiquesPireLivreur(int id_utilisateur, String nom, String prenom, String email, String motdepasse, String immatriculation, int nbRetards) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.immatriculation = immatriculation;
        this.nbRetards = nbRetards;
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

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getNbRetards() {
        return nbRetards;
    }

    public void setNbRetards(int nbRetards) {
        this.nbRetards = nbRetards;
    }
}

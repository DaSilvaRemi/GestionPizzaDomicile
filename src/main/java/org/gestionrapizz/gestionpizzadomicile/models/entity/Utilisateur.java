package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Utilisateur {
    int id;
    String nom;
    String prenom;
    String email;
    String motdepasse;

    public Utilisateur(int id, String nom, String prenom, String email, String motdepasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
    }

    public Utilisateur(Utilisateur utilisateur){
        this(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMotdepasse());
    }

    public final Utilisateur getUtilisateur(){
        return new Utilisateur(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

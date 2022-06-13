package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Livreur extends Utilisateur {
    public Livreur(int id, Utilisateur utilisateur) {
        this(id, utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMotdepasse());
    }

    public Livreur(int id, String nom, String prenom, String email, String motdepasse) {
        super(id, nom, prenom, email, motdepasse);
    }

    public Livreur(Livreur livreur){
        this(livreur.getId(), livreur.getUtilisateur());
    }

    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

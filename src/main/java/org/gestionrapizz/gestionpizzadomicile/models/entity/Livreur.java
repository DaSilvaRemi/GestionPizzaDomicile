package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Livreur extends Utilisateur {
    public Livreur(int id, Utilisateur utilisateur) {
        this(id, utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getMotdepasse());
    }

    public Livreur(int id, String nom, String email, String motdepasse) {
        super(id, nom, email, motdepasse);
    }



    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

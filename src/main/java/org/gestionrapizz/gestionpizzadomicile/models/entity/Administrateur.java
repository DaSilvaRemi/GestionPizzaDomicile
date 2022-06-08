package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Administrateur extends Utilisateur {
    public Administrateur(int id, String nom, String prenom, String email, String motdepasse) {
        super(id, nom, prenom, email, motdepasse);
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

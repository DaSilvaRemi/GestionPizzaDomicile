package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Administrateur extends Utilisateur {
    public Administrateur(int id, String nom, String email, String motdepasse) {
        super(id, nom, email, motdepasse);
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

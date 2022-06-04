package org.gestionrapizz.gestionpizzadomicile.models.entity;

public class Client extends Utilisateur {
    private String telephone;
    private String adresseRue;
    private String ville;
    private String codePostal;
    private Double solde;

    public Client(Utilisateur utilisateur, String telephone, String adresseRue, String ville, String codePostal) {
        this(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMotdepasse(), telephone, adresseRue, ville, codePostal, 0.0);
    }

    public Client(int id, String nom, String prenom, String email, String motdepasse, String telephone, String adresseRue, String ville, String codePostal) {
        this(id, nom, prenom, email, motdepasse, telephone, adresseRue, ville, codePostal, 0.0);
    }

    public Client(int id, String nom, String prenom, String email, String motdepasse, String telephone, String adresseRue, String ville, String codePostal, Double solde) {
        super(id, nom, prenom, email, motdepasse);
        this.telephone = telephone;
        this.adresseRue = adresseRue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.solde = solde;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresseRue() {
        return adresseRue;
    }

    public void setAdresseRue(String adresseRue) {
        this.adresseRue = adresseRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Client{" +
                "telephone='" + telephone + '\'' +
                ", adresseRue='" + adresseRue + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", solde=" + solde +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}

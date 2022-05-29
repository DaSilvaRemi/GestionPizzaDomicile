package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ClientModel extends GestionBDDModele {

    public ClientModel(){
        super();
    }

    public ClientModel(String urlHote, String user, String motdepasse) {
        super(urlHote, user, motdepasse);
    }

    public ResultSet getInfosClient() throws SQLException {
        super.setMyStatement("SELECT u.nom, u.email, c.telephone, c.adresse_rue, c.adresse_ville, c.adresse_codepostal, c.solde " +
                "FROM client c INNER JOIN utilisateur u ON c.id_utilisateur = u.id_utilisateur;");
        return super.getRequestResult();
    }

    public ResultSet getInfosClientsById(int id) throws SQLException {
        super.setMyStatement("SELECT u.nom, u.email, c.telephone, c.adresse_rue, c.adresse_ville, c.adresse_codepostal, c.solde " +
                "FROM client c INNER JOIN utilisateur u ON c.id_utilisateur = u.id_utilisateur " +
                "WHERE c.id_utilisateur = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getRequestResult();
    }

    public ResultSet getInfosClientsBytelephone(String telephone) throws SQLException {
        super.setMyStatement("SELECT u.nom, u.email, c.telephone, c.adresse_rue, c.adresse_ville, c.adresse_codepostal, c.solde " +
                "FROM client c INNER JOIN utilisateur u ON c.id_utilisateur = u.id_utilisateur " +
                "WHERE c.telephone = ?;");
        super.getMyStatement().setString(1, telephone);
        return super.getRequestResult();
    }

    public void insertClient(String nom, String adresse, String codePostal, String ville, String telephone, String email, String motdepasse) throws SQLException {
        super.disconnect();

        UtilisateurModel utilisateurModel = new UtilisateurModel();
        utilisateurModel.connect();
        utilisateurModel.insertUser(email, motdepasse);
        int idUtilisateur = utilisateurModel.getUtilisateurByEmail(email).getInt("id_utilisateur");
        utilisateurModel.disconnect();

        super.connect();
        super.setMyStatement("INSERT INTO client(id_utilisateur, nom, adress_rue, adresse_codepostal, adresse_ville, telephone) VALUES(?, ?, ?, ?, ?, ?)");
        super.getMyStatement().setInt(1, idUtilisateur);
        super.getMyStatement().setString(2, nom);
        super.getMyStatement().setString(3, adresse);
        super.getMyStatement().setString(4, codePostal);
        super.getMyStatement().setString(5, ville);
        super.getMyStatement().setString(6, telephone);
        super.getMyStatement().setString(7, email);
        super.getMyStatement().setString(8, motdepasse);
        super.executeRequest();
    }
}

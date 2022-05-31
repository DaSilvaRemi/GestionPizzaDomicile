package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurModel extends GestionBDDModel {
    public ResultSet getUtilisateurById(int id) throws SQLException {
        super.setMyStatement("SELECT u.id_utilisateur, u.nom, u.email, u.is_admin FROM utilisateur FROM utilisateur u WHERE u.id_utilisateur = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }

    public ResultSet getUtilisateurByEmail(String email) throws SQLException {
        super.setMyStatement("SELECT u.id_utilisateur, u.nom, u.email, u.is_admin FROM utilisateur FROM utilisateur u WHERE u.email = ?;");
        super.getMyStatement().setString(1, email);
        return super.getQueryResult();
    }

    public ResultSet countUtilisateurWithEmail(String email) throws SQLException {
        super.setMyStatement("SELECT COUNT(u.*) AS nbUsersWithEmails FROM utilisateur u WHERE u.email = ?;");
        super.getMyStatement().setString(1, email);
        return super.getQueryResult();
    }

    public ResultSet countUtilisateurWithEmailAndPassword(String email, String password) throws SQLException {
        super.setMyStatement("SELECT COUNT(u.*) AS nbUsersWithGoodCredential FROM utilisateur u WHERE u.email = ? AND u.motdepasse = ?;");
        super.getMyStatement().setString(1, email);
        super.getMyStatement().setString(2, password);
        return super.getQueryResult();
    }

    public void insertUser(String email, String password) throws SQLException {
        this.insertUser(email, password, false);
    }

    public void insertUser(String email, String password, Boolean isAdmin) throws SQLException {
        super.setMyStatement("INSERT INTO utilisateur (email, motdepasse, is_admin) VALUES(?, ?, ?);");
        super.getMyStatement().setString(1, email);
        super.getMyStatement().setString(2, password);
        super.getMyStatement().setBoolean(3, isAdmin);
    }

    public void updateUser(int idUtilisateur, String email, String password, Boolean isAdmin) throws SQLException {
        super.setMyStatement("UPDATE utilisateur AS u SET email = ?, motdepasse = ?, is_admin = ? WHERE id_utilisateur = ?;");
        super.getMyStatement().setString(1, email);
        super.getMyStatement().setString(2, password);
        super.getMyStatement().setBoolean(3, isAdmin);
        super.getMyStatement().setInt(4, idUtilisateur);
    }
}

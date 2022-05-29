package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurModel extends GestionBDDModele {
    public UtilisateurModel(){
        super();
    }

    public ResultSet getUtilisateurById(int id) throws SQLException {
        super.setMyStatement("SELECT u.id, u.name, u.emailAdress FROM users FROM utilisateur u WHERE u.id = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getRequestResult();
    }

    public ResultSet getUtilisateurByEmail(String email) throws SQLException {
        super.setMyStatement("SELECT u.id, u.name, u.emailAdress FROM users FROM utilisateur u WHERE u.email = ?;");
        super.getMyStatement().setString(1, email);
        return super.getRequestResult();
    }

    public ResultSet countUtilisateurWithEmail(String email) throws SQLException {
        super.setMyStatement("SELECT COUNT(u.*) AS nbUsersWithEmails FROM utilisateur u WHERE u.email = ?;");
        super.getMyStatement().setString(1, email);
        return super.getRequestResult();
    }

    public ResultSet countUtilisateurWithEmailAndPassword(String email, String password) throws SQLException {
        super.setMyStatement("SELECT COUNT(u.*) AS nbUsersWithGoodCredential FROM utilisateur u WHERE u.email = ? AND u.motdepasse = ?;");
        super.getMyStatement().setString(1, email);
        super.getMyStatement().setString(2, password);
        return super.getRequestResult();
    }

    public void insertUser(String email, String password) throws SQLException {
        this.insertUser(email, password, false);
    }

    public void insertUser(String email, String password, Boolean isAdmin) throws SQLException {
        super.setMyStatement("INSERT INTO utilisateur (u.email, u.motdepasse, u.is_admin) VALUES(?, ?, ?);");
        super.getMyStatement().setString(1, email);
        super.getMyStatement().setString(2, password);
        super.getMyStatement().setBoolean(3, isAdmin);
    }
}

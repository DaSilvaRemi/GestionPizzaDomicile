package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel extends GestionBDDModele {
    public UserModel(){
        super();
    }

    public ResultSet getUserById(int id) throws SQLException {
        super.setMyStatement("SELECT u.name, u.emailAdress FROM users FROM user u WHERE id = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getRequestResult();
    }

    public ResultSet getUserByUserName(String emailAdress) throws SQLException {
        super.setMyStatement("SELECT u.name FROM users FROM user u WHERE emailAdress = ?;");
        super.getMyStatement().setString(1, emailAdress);
        return super.getRequestResult();
    }

    public ResultSet countUserWithEmailAdress(String emailAdress) throws SQLException {
        super.setMyStatement("SELECT COUNT(*) AS nbUsersWithEmails FROM users FROM user u WHERE emailAdress = ?;");
        super.getMyStatement().setString(1, emailAdress);
        return super.getRequestResult();
    }

    public ResultSet countUserWithEmailAdressAndPassword(String emailAdress, String password) throws SQLException {
        super.setMyStatement("SELECT COUNT(*) AS nbUsersWithGoodCredential FROM users FROM user u WHERE emailAdress = ? AND password = ?;");
        super.getMyStatement().setString(1, emailAdress);
        super.getMyStatement().setString(2, password);
        return super.getRequestResult();
    }

    public void insertUser(String emailAdress, String password) throws SQLException {
        super.setMyStatement("INSERT INTO user (idUsers, emailAdress, password) VALUES(DEFAULT, ?, ?);");
        super.getMyStatement().setString(1, emailAdress);
        super.getMyStatement().setString(2, password);
    }
}

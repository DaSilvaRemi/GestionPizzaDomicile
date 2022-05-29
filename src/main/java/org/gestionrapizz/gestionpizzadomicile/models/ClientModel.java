package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ClientModel extends GestionBDDModele {

    public ClientModel(){
        super();
    }

    public ClientModel(String urlHote, String user, String password) {
        super(urlHote, user, password);
    }

    public ResultSet getInfosClient() throws SQLException {
        return super.setMyStatement("SELECT c.nom, c.adresse, c.city, c.zipCode c.phoneNumber, c.solde " +
                "FROM client c;").getRequestResult();
    }

    public ResultSet getInfosClientsById(int id) throws SQLException {
        super.setMyStatement("SELECT c.nom, c.adresse, c.city, c.zipCode, c.phoneNumber, c.solde " +
                "FROM client c WHERE id = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getRequestResult();
    }

    public ResultSet getInfosClientsByPhoneNumber(String phoneNumber) throws SQLException {
        super.setMyStatement("ELECT c.nom, c.adresse, c.city, c.zipCode, c.phoneNumber, c.solde " +
                "FROM client c WHERE telephone = ?;");
        super.getMyStatement().setString(1, phoneNumber);
        return super.getRequestResult();
    }

    public void insertClient(String nom, String adresse, String zipCode, String city, String phoneNumber, String emailAdress, String password) throws SQLException {
        super.setMyStatement("INSERT INTO client(nom, adresse, zipCode, city, phoneNumber) VALUES(?, ?, ?, ?, ?)");
        super.getMyStatement().setString(1, nom);
        super.getMyStatement().setString(2, adresse);
        super.getMyStatement().setString(3, zipCode);
        super.getMyStatement().setString(4, city);
        super.getMyStatement().setString(5, phoneNumber);
        super.getMyStatement().setString(6, emailAdress);
        super.getMyStatement().setString(7, password);
        super.executeRequest();
    }
}

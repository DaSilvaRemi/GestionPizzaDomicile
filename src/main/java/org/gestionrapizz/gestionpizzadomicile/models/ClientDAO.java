package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientDAO extends DAO<Client> {
    private static ClientDAO instance;

    private ClientDAO() {
    }

    public static ClientDAO getInstance() {
        if(ClientDAO.instance == null){
            ClientDAO.instance = new ClientDAO();
        }

        return ClientDAO.instance;
    }

    @Override
    public List<Client> get() {
        String query = "SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde " +
                "FROM Client " +
                "INNER JOIN Utilisateur ON Client.id_utilisateur = Utilisateur.id_utilisateur;";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Client getById(int id) {
        String query = "SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde " +
                "FROM Client " +
                "INNER JOIN Utilisateur ON Client.id_utilisateur = Utilisateur.id_utilisateur " +
                "WHERE Utilisateur.id_utilisateur = ?;";
        return super.find(query, List.of(id)).get(0);
    }

    public Client getByEmail(String email) {
        String query = "SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde " +
                "FROM Client " +
                "INNER JOIN Utilisateur ON Client.id_utilisateur = Utilisateur.id_utilisateur " +
                "WHERE Utilisateur.email = ?;";
        return super.find(query, List.of(email)).get(0);
    }

    public Client getByTelephone(String telephone) {
        String query = "SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde " +
                "FROM Client " +
                "INNER JOIN Utilisateur ON Client.id_utilisateur = Utilisateur.id_utilisateur" +
                "WHERE Client.id_telephone = ?;";
        return super.find(query, List.of(telephone)).get(0);
    }

    @Override
    public int insert(Client obj) {
        int idUser = UtilisateurDAO.getInstance().insert(obj.getUtilisateur());
        if(idUser == 0) return 0;

        String query = "INSERT INTO Client (id_utilisateur, telephone, adresse_rue, adresse_ville, adresse_codepostal, solde) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        List<Object> params = Arrays.asList(
                idUser,
                obj.getTelephone(),
                obj.getAdresseRue(),
                obj.getVille(),
                obj.getCodePostal(),
                obj.getSolde());
        return super.add(query, params);
    }

    @Override
    public boolean update(Client obj) {
        UtilisateurDAO.getInstance().update(obj.getUtilisateur());
        String query = "UPDATE Client " +
                "SET telephone = ?, " +
                "adresse_rue = ?, " +
                "adresse_ville = ?, " +
                "adresse_codepostal = ?, " +
                "solde = ?," +
                "WHERE id_utilisateur = ?;";
        List<Object> params = Arrays.asList(
                obj.getTelephone(),
                obj.getAdresseRue(),
                obj.getVille(),
                obj.getCodePostal(),
                obj.getSolde(),
                obj.getId());
        return super.modify(query, params) > 0;
    }

    @Override
    public boolean delete(Client obj) {
        String query = "DELETE FROM Client WHERE Client.id_utilisateur = ?;";
        List<Object> params = List.of(obj.getId());
        return super.modify(query, params) > 0 && UtilisateurDAO.getInstance().delete(obj.getUtilisateur());
    }

    @Override
    public Client resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("id_utilisateur"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getString("motdepasse"),
                resultSet.getString("telephone"),
                resultSet.getString("adresse_rue"),
                resultSet.getString("adresse_ville"),
                resultSet.getString("adresse_codepostal"),
                resultSet.getDouble("solde")
        );

    }
}

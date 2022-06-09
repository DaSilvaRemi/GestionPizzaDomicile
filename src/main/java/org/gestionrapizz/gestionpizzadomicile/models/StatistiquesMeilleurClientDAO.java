package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesMeilleurClient;
import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesPizzaPlusDemandee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesMeilleurClientDAO extends DAO<StatistiquesMeilleurClient>{
    private static StatistiquesMeilleurClientDAO instance;

    private StatistiquesMeilleurClientDAO() {
    }

    public static StatistiquesMeilleurClientDAO getInstance() {
        if(StatistiquesMeilleurClientDAO.instance == null){
            StatistiquesMeilleurClientDAO.instance = new StatistiquesMeilleurClientDAO();
        }

        return StatistiquesMeilleurClientDAO.instance;
    }

    @Override
    public List<StatistiquesMeilleurClient> get() {
        String query = "SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville, Client.solde, SUM(Commande.montant) AS montantTotal " +
            "FROM Utilisateur " +
            "INNER JOIN Client on Utilisateur.id_utilisateur = Client.id_utilisateur " +
            "INNER JOIN Commande on Client.id_utilisateur = Commande.id_utilisateur_1 " +
            "GROUP BY Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville, Client.solde " +
            "ORDER BY montantTotal DESC " +
            "LIMIT 1;";
        return super.find(query, new ArrayList<>());
    }

    public StatistiquesMeilleurClient getMeilleurClient() {
        List<StatistiquesMeilleurClient> result = this.get();
        return result.size() >= 1 ? result.get(0) : null;
    }

    @Override
    public StatistiquesMeilleurClient getById(int id) {
        return null;
    }

    @Override
    public int insert(StatistiquesMeilleurClient obj) {
        return 0;
    }

    @Override
    public boolean update(StatistiquesMeilleurClient obj) {
        return false;
    }

    @Override
    public boolean delete(StatistiquesMeilleurClient obj) {
        return false;
    }

    @Override
    public StatistiquesMeilleurClient resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new StatistiquesMeilleurClient(
            resultSet.getInt("Utilisateur.id_utilisateur"),
            resultSet.getString("Utilisateur.nom"),
            resultSet.getString("Utilisateur.prenom"),
            resultSet.getString("Utilisateur.email"),
            resultSet.getString("Utilisateur.motdepasse"),
            resultSet.getString("Client.telephone"),
            resultSet.getString("Client.adresse_rue"),
            resultSet.getString("Client.adresse_codepostal"),
            resultSet.getString("Client.adresse_ville"),
            resultSet.getDouble("Client.solde"),
            resultSet.getInt("montantTotal")
        );
    }
}

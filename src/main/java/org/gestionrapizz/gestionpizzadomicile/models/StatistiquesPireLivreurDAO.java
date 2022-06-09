package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesMeilleurClient;
import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesPireLivreur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesPireLivreurDAO extends DAO<StatistiquesPireLivreur>{
    private static StatistiquesPireLivreurDAO instance;

    private StatistiquesPireLivreurDAO() {
    }

    public static StatistiquesPireLivreurDAO getInstance() {
        if(StatistiquesPireLivreurDAO.instance == null){
            StatistiquesPireLivreurDAO.instance = new StatistiquesPireLivreurDAO();
        }

        return StatistiquesPireLivreurDAO.instance;
    }

    @Override
    public List<StatistiquesPireLivreur> get() {
        String query = "SELECT Utilisateur.*, Commande.immatriculation, COUNT(Commande.retard) AS nbRetards FROM Commande " +
            "INNER JOIN Livreur on Commande.id_utilisateur = Livreur.id_utilisateur " +
            "INNER JOIN Utilisateur on Livreur.id_utilisateur = Utilisateur.id_utilisateur " +
            "WHERE Commande.retard = 1 " +
            "GROUP BY Commande.immatriculation " +
            "ORDER BY nbRetards DESC LIMIT 1;";
        return super.find(query, new ArrayList<>());
    }

    public StatistiquesPireLivreur getPireLivreur() {
        List<StatistiquesPireLivreur> result = this.get();
        return result.size() >= 1 ? result.get(0) : null;
    }

    @Override
    public StatistiquesPireLivreur getById(int id) {
        return null;
    }

    @Override
    public int insert(StatistiquesPireLivreur obj) {
        return 0;
    }

    @Override
    public boolean update(StatistiquesPireLivreur obj) {
        return false;
    }

    @Override
    public boolean delete(StatistiquesPireLivreur obj) {
        return false;
    }

    @Override
    public StatistiquesPireLivreur resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new StatistiquesPireLivreur(
            resultSet.getInt("Utilisateur.id_utilisateur"),
            resultSet.getString("Utilisateur.nom"),
            resultSet.getString("Utilisateur.prenom"),
            resultSet.getString("Utilisateur.email"),
            resultSet.getString("Utilisateur.motdepasse"),
            resultSet.getString("Commande.immatriculation"),
            resultSet.getInt("nbRetards")
        );
    }
}

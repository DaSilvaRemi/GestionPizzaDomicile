package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StatistiquesNombrePizzaAcheteesDAO extends DAO<Integer>{
    private static StatistiquesNombrePizzaAcheteesDAO instance;

    private StatistiquesNombrePizzaAcheteesDAO() {
    }

    public static StatistiquesNombrePizzaAcheteesDAO getInstance() {
        if(StatistiquesNombrePizzaAcheteesDAO.instance == null){
            StatistiquesNombrePizzaAcheteesDAO.instance = new StatistiquesNombrePizzaAcheteesDAO();
        }

        return StatistiquesNombrePizzaAcheteesDAO.instance;
    }
    public int getNbPizzaAchetees() {
        String query = "SELECT Count(*) AS nbPizzaCA FROM Commande " +
                "JOIN Statut ON Statut.id_statut=Commande.id_statut " +
                "WHERE Commande.retard=? AND Statut.nom!=?;";
        List<Integer> result = super.find(query, Arrays.asList(0, "Refusé"));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public List<Integer> get() {
        return null;
    }

    @Override
    public Integer getById(int id) {
        return null;
    }

    @Override
    public int insert(Integer obj) {
        return 0;
    }

    @Override
    public boolean update(Integer obj) {
        return false;
    }

    @Override
    public boolean delete(Integer obj) {
        return false;
    }

    @Override
    public Integer resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("nbPizzaCA");
    }
}

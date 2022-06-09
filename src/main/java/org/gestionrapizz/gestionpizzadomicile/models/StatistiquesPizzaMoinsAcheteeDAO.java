package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesPizzaMoinsAchetee;
import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesPizzaPlusDemandee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesPizzaMoinsAcheteeDAO extends DAO<StatistiquesPizzaMoinsAchetee>{
    private static StatistiquesPizzaMoinsAcheteeDAO instance;

    private StatistiquesPizzaMoinsAcheteeDAO() {
    }

    public static StatistiquesPizzaMoinsAcheteeDAO getInstance() {
        if(StatistiquesPizzaMoinsAcheteeDAO.instance == null){
            StatistiquesPizzaMoinsAcheteeDAO.instance = new StatistiquesPizzaMoinsAcheteeDAO();
        }

        return StatistiquesPizzaMoinsAcheteeDAO.instance;
    }
    @Override
    public List<StatistiquesPizzaMoinsAchetee> get() {
        String query = "SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir " +
            "INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza " +
            "GROUP BY Pizza.nom " +
            "ORDER BY nbDemandes ASC;";
        return super.find(query, new ArrayList<>());
    }

    public StatistiquesPizzaMoinsAchetee getPizzaMoinsAchetee() {
        List<StatistiquesPizzaMoinsAchetee> result = this.get();
        return result.size() >= 1 ? result.get(0) : null;
    }

    @Override
    public StatistiquesPizzaMoinsAchetee getById(int id) {
        return null;
    }

    @Override
    public int insert(StatistiquesPizzaMoinsAchetee obj) {
        return 0;
    }

    @Override
    public boolean update(StatistiquesPizzaMoinsAchetee obj) {
        return false;
    }

    @Override
    public boolean delete(StatistiquesPizzaMoinsAchetee obj) {
        return false;
    }

    @Override
    public StatistiquesPizzaMoinsAchetee resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new StatistiquesPizzaMoinsAchetee(
            resultSet.getString("nomPizza"),
            resultSet.getInt("nbDemandes")
        );
    }
}

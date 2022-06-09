package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesPizzaPlusDemandee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatistiquesPizzaPlusDemandeeDAO extends DAO<StatistiquesPizzaPlusDemandee>{

    private static StatistiquesPizzaPlusDemandeeDAO instance;

    private StatistiquesPizzaPlusDemandeeDAO() {
    }

    public static StatistiquesPizzaPlusDemandeeDAO getInstance() {
        if(StatistiquesPizzaPlusDemandeeDAO.instance == null){
            StatistiquesPizzaPlusDemandeeDAO.instance = new StatistiquesPizzaPlusDemandeeDAO();
        }

        return StatistiquesPizzaPlusDemandeeDAO.instance;
    }

    @Override
    public List<StatistiquesPizzaPlusDemandee> get() {
        String query = "SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir " +
            "INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza " +
            "GROUP BY Pizza.nom " +
            "ORDER BY nbDemandes DESC;";
        return super.find(query, new ArrayList<>());
    }

    public StatistiquesPizzaPlusDemandee getPizzaPlusDemandeeTOP1() {
        List<StatistiquesPizzaPlusDemandee> result = this.get();
        return result.size() >= 1 ? result.get(0) : null;
    }

    @Override
    public StatistiquesPizzaPlusDemandee getById(int id) {
        return null;
    }

    @Override
    public int insert(StatistiquesPizzaPlusDemandee obj) {
        return 0;
    }

    @Override
    public boolean update(StatistiquesPizzaPlusDemandee obj) {
        return false;
    }

    @Override
    public boolean delete(StatistiquesPizzaPlusDemandee obj) {
        return false;
    }

    @Override
    public StatistiquesPizzaPlusDemandee resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new StatistiquesPizzaPlusDemandee(
            resultSet.getString("nomPizza"),
            resultSet.getInt("nbDemandes")
        );
    }
}

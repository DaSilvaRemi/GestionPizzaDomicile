package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesMeilleurClient;
import org.gestionrapizz.gestionpizzadomicile.models.view.StatistiquesMeilleurIngredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesMeilleurIngredientDAO  extends DAO<StatistiquesMeilleurIngredient>{
    private static StatistiquesMeilleurIngredientDAO instance;

    private StatistiquesMeilleurIngredientDAO() {
    }

    public static StatistiquesMeilleurIngredientDAO getInstance() {
        if(StatistiquesMeilleurIngredientDAO.instance == null){
            StatistiquesMeilleurIngredientDAO.instance = new StatistiquesMeilleurIngredientDAO();
        }

        return StatistiquesMeilleurIngredientDAO.instance;
    }

    @Override
    public List<StatistiquesMeilleurIngredient> get() {
        String query = "SELECT Ingredients.nom AS nomIngredient, COUNT(Composer.id_ingredient) AS nbIngredients FROM Ingredients " +
            "INNER JOIN Composer ON Ingredients.id_ingredient = Composer.id_ingredient " +
            "INNER JOIN Pizza ON Composer.id_pizza = Pizza.id_pizza " +
            "INNER JOIN Produit ON Pizza.id_pizza = Produit.id_pizza " +
            "INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza " +
            "INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande " +
            "GROUP BY Ingredients.nom " +
            "ORDER BY nbIngredients DESC;";
        return super.find(query, new ArrayList<>());
    }

    public StatistiquesMeilleurIngredient getMeilleurIngredient() {
        List<StatistiquesMeilleurIngredient> result = this.get();
        return result.size() >= 1 ? result.get(0) : null;
    }

    @Override
    public StatistiquesMeilleurIngredient getById(int id) {
        return null;
    }

    @Override
    public int insert(StatistiquesMeilleurIngredient obj) {
        return 0;
    }

    @Override
    public boolean update(StatistiquesMeilleurIngredient obj) {
        return false;
    }

    @Override
    public boolean delete(StatistiquesMeilleurIngredient obj) {
        return false;
    }

    @Override
    public StatistiquesMeilleurIngredient resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new StatistiquesMeilleurIngredient(
                resultSet.getString("nomIngredient"),
                resultSet.getInt("nbIngredients")
        );
    }
}
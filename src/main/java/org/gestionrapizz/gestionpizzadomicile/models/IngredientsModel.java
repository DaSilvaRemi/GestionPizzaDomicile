package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsModel extends GestionBDDModel {
    public ResultSet getAllIngredients() throws SQLException {
        return super.setMyStatement("SELECT i.id_ingredient, i.nom FROM ingredients i;").getQueryResult();
    }

    public ResultSet getAllIngredientsById(int id) throws SQLException {
        super.setMyStatement("SELECT i.id_ingredient, i.nom FROM ingredients i WHERE i.id_ingredient = ?;");
        super.getMyStatement().setInt(1, id);
        return this.getQueryResult();
    }

    public ResultSet getMostUsedIngredients() throws SQLException {
        super.setMyStatement("SELECT ingredients.id_ingredient, ingredients.nom, COUNT(contenir.id_ingredient) AS nbIngredients FROM ingredients " +
                "INNER JOIN contenir ON contenir.id_ingredient = ingredients.id_ingredient " +
                "INNER JOIN pizza ON contenir.id_pizza = pizza.id_pizza " +
                "INNER JOIN produit ON produit.id_pizza = pizza.id_pizza " +
                "INNER JOIN avoir ON avoir.id_pîzza = pizza.id_pizza " +
                "INNER JOIN commande ON commande.id_commande = avoir.id_commande " +
                "WHERE commande.statut = ?" +
                "GROUP BY ingredients.id_ingredient, ingredients.nom " +
                "ORDER BY nbIngredients DESC " +
                "LIMIT 5");
        super.getMyStatement().setString(1, "Livree");
        return super.getQueryResult();
    }

    public void updateIngredient(int id, String newName) throws SQLException{
        super.setMyStatement("UPDATE ingredients SET ingredients.name = ? WHERE ingredients.id_ingredient = ?");
        super.getMyStatement().setString(1, newName);
        super.getMyStatement().setInt(2, id);
        this.executeQuery();
    }

    public void deleteIngredient(int id) throws SQLException{
        super.setMyStatement("DELETE FROM ingredients WHERE ingredients.id_ingredient = ?");
        super.getMyStatement().setInt(1, id);
        this.executeQuery();
    }
}

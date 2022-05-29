package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientModel extends GestionBDDModele {
    public IngredientModel(){
        super();
    }

    public IngredientModel(String urlHote, String user, String password) {
        super(urlHote, user, password);
    }

    public ResultSet getAllIngredients() throws SQLException {
        return super.setMyStatement("SELECT i.id_ingredient, i.nom FROM ingredient i;").getRequestResult();
    }

    public ResultSet getAllIngredientsById(int id) throws SQLException {
        super.setMyStatement("SELECT i.id_ingredient, i.nom FROM ingredient i WHERE i.idIngredient = ?;");
        super.getMyStatement().setInt(1, id);
        return this.getRequestResult();
    }

    public ResultSet getMostUsedIngredients() throws SQLException {
        super.setMyStatement("SELECT ingredient.id_ingredient, ingredient.nom, COUNT(contenir.id_ingredient) AS nbIngredients FROM ingredient " +
                "INNER JOIN contenir ON contenir.id_ingredient = ingredient.id_ingredient " +
                "INNER JOIN pizza ON contenir.id_pizza = pizza.id_pizza " +
                "INNER JOIN produit ON produit.id_pizza = pizza.id_pizza " +
                "INNER JOIN avoir ON avoir.id_pîzza = pizza.id_pizza " +
                "INNER JOIN commande ON commande.id_commande = avoir.id_commande " +
                "WHERE commande.statut = ?" +
                "GROUP BY ingredient.id_ingredient, ingredient.nom " +
                "ORDER BY nbIngredients DESC " +
                "LIMIT 5");
        super.getMyStatement().setString(1, "Livree");
        return super.getRequestResult();
    }

    public void updateIngredient(int id, String newName) throws SQLException{
        super.setMyStatement("UPDATE ingredient SET ingredient.name = ? WHERE ingredient.id_ingredient = ?");
        super.getMyStatement().setString(1, newName);
        super.getMyStatement().setInt(2, id);
        this.executeRequest();
    }

    public void deleteIngredient(int id) throws SQLException{
        super.setMyStatement("DELETE FROM ingredient WHERE ingredient.id_ingredient = ?");
        super.getMyStatement().setInt(1, id);
        this.executeRequest();
    }
}
